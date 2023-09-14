package br.com.fuzus.avanadedesafiorpg.domain.battle.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.BattleStatus;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.InteractInBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.StartBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleInitiativeResultResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStartedResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStatusResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.repository.BattleRepository;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.CalculateDamage;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.DamageActions;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.PlayerActions;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.dice.DiceRoll;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations.*;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import br.com.fuzus.avanadedesafiorpg.domain.character.service.CharacterService;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Subject;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import br.com.fuzus.avanadedesafiorpg.domain.turn.service.TurnService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BattleServiceImp implements BattleService {

    private final BattleRepository battleRepository;
    private final BattleHistoryService battleHistoryService;
    private final CharacterService characterService;
    private final TurnService turnService;
    private final Environment environment;

    @Override
    public BattleStartedResponse startBattle(StartBattleDto dto) {
        var hero = this.characterService.getById(dto.chosenHero());
        Character monster;
        if (dto.chosenMonster() != null) {
            monster = this.characterService.getById(dto.chosenMonster());
        } else {
            monster = this.characterService.createRandomMonster(DiceRoll.diceRoll(Dice.D3));
        }
        var battle = new Battle(hero, monster);
        battle = this.battleRepository.save(battle);
        return new BattleStartedResponse(battle, environment.getProperty("battle.started"));
    }

    @Override
    public BattleInitiativeResultResponse diceInitiative(InteractInBattleDto dto) {
        var battle = this.battleHistoryService.getBattleById(dto.id());
        this.doTurnValidations(new ValidateInitiativeRolled(battle, environment.getProperty("battle.initiative.error.AlreadyRolled")));
        Subject nextAttacker;
        do {
            var playerInitiative = DiceRoll.diceRoll(Dice.D20);
            var monsterInitiative = DiceRoll.diceRoll(Dice.D20);

            battle.setPlayerInitiative(playerInitiative);
            battle.setMonsterInitiative(monsterInitiative);
            nextAttacker = playerInitiative > monsterInitiative ? Subject.HERO : Subject.MONSTER;

        } while (battle.getPlayerInitiative().equals(battle.getMonsterInitiative()));
        var turn = new Turn(null, 1L, null, null, battle, nextAttacker);
        battle.getTurns().add(turn);
        battle.setStatus(BattleStatus.ON_GOING);
        this.turnService.createTurn(turn, battle);
        battle = this.battleRepository.save(battle);
        return new BattleInitiativeResultResponse(battle, String.format(Objects.requireNonNull(environment.getProperty("battle.initiative")), nextAttacker));
    }

    @Override
    public BattleStatusResponse attack(InteractInBattleDto dto) {
        return this.act(dto, PlayerActions.ATTACK);
    }

    @Override
    public BattleStatusResponse defend(InteractInBattleDto dto) {
        return this.act(dto, PlayerActions.DEFEND);
    }

    private BattleStatusResponse act(InteractInBattleDto dto, PlayerActions playerAction) {
        var battle = this.battleHistoryService.getBattleById(dto.id());

        this.doTurnValidations(new ValidateEndGame(battle, environment.getProperty("battle.ended")),
                new ValidateInitiativeNotRolled(battle, environment.getProperty("battle.initiative.error.notRolledYet")));

        var actualTurn = this.turnService.getActualTurn(battle);
        String nextTurnAction = playerAction == PlayerActions.DEFEND ?
                environment.getProperty("battle.turn.attack") : environment.getProperty("battle.turn.defend");

        if (playerAction == PlayerActions.ATTACK) {
            this.doTurnValidations(new ValidatePlayerCanAttack(actualTurn, environment.getProperty("battle.turn.attack.error")));
            var damageDealt = this.attack(battle.getHero(), battle.getMonster());
            actualTurn.setDamageDealt(damageDealt);

            if (battle.getMonster().getLifePoints() <= 0) {
                battle.setStatus(BattleStatus.VICTORY);
                battle.getMonster().setLifePoints(0);
                nextTurnAction = environment.getProperty("battle.ended.victory");
            }
        } else {
            this.doTurnValidations(new ValidatePlayerCanDefend(actualTurn, environment.getProperty("battle.turn.defend.error")));
            var damageReceived = this.attack(battle.getMonster(), battle.getHero());
            actualTurn.setDamageReceived(damageReceived);

            if (battle.getHero().getLifePoints() <= 0) {
                battle.setStatus(BattleStatus.DEFEATED);
                battle.getHero().setLifePoints(0);
                nextTurnAction = environment.getProperty("battle.ended.defeated");
            }
        }

        actualTurn = this.turnService.updateTurn(actualTurn);
        battle = this.battleRepository.save(battle);

        return new BattleStatusResponse(actualTurn, battle.getHero(), battle.getMonster(), nextTurnAction);
    }

    private void doTurnValidations(ValidationTurn... validations) {
        for (ValidationTurn validation : validations) {
            validation.validate();
        }
    }

    private Integer attack(Character subject, Character target) {
        int damageDealt = 0;
        AttackValidations attack = new TryToAttack(subject, target);
        if (attack.validate()) {
            DamageActions damage = new CalculateDamage(subject);
            damageDealt = damage.perform();
            target.setLifePoints(target.getLifePoints() - damageDealt);
        }
        return damageDealt;
    }
}
