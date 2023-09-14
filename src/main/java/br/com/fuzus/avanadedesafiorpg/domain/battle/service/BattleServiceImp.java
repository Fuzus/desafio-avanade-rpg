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
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.dice.DiceRoll;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations.*;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.request.CreateCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.service.CharacterService;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Subject;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import br.com.fuzus.avanadedesafiorpg.domain.turn.service.TurnService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattleServiceImp implements BattleService {

    private final BattleRepository battleRepository;
    private final BattleHistoryService battleHistoryService;
    private final CharacterService characterService;
    private final TurnService turnService;

    @Override
    public BattleStartedResponse startBattle(StartBattleDto dto) {
        var hero = this.characterService.getById(dto.chosenHero());
        Character monster;
        if (dto.chosenMonster() != null) {
            monster = this.characterService.getById(dto.chosenMonster());
        } else {
            //TODO: Criar o monstro de forma aleatória
            monster = this.characterService.createOrc(new CreateCharacterDto("josnei"));
        }
        var battle = new Battle(hero, monster);
        battle = this.battleRepository.save(battle);
        return new BattleStartedResponse(battle, "Batalha iniciada, role iniciativa");
    }

    @Override
    public BattleInitiativeResultResponse diceInitiative(InteractInBattleDto dto) {
        var battle = this.battleHistoryService.getBattleById(dto.id());
        this.doTurnValidations(new ValidateInitiativeRolled(battle));
        String nextAttacker;
        do {
            var playerInitiative = DiceRoll.diceRoll(Dice.D20);
            var monsterInitiative = DiceRoll.diceRoll(Dice.D20);

            battle.setPlayerInitiative(playerInitiative);
            battle.setMonsterInitiative(monsterInitiative);
            nextAttacker = playerInitiative > monsterInitiative ? "HERO" : "MONSTER";

        } while (battle.getPlayerInitiative().equals(battle.getMonsterInitiative()));
        var turn = new Turn(null, 1L, null, null, battle, Subject.valueOf(nextAttacker));
        battle.getTurns().add(turn);
        battle.setStatus(BattleStatus.ON_GOING);
        this.turnService.createTurn(turn, battle);
        battle = this.battleRepository.save(battle);
        return new BattleInitiativeResultResponse(battle, String.format("%s inicia atacando", nextAttacker));
    }

    @Override
    public BattleStatusResponse attack(InteractInBattleDto dto) {
        var battle = this.battleHistoryService.getBattleById(dto.id());
        var actualTurn = this.turnService.getActualTurn(battle);

        this.doTurnValidations(new ValidateEndGame(battle), new ValidatePlayerCanAttack(actualTurn));

        var damageDealt = this.attack(battle.getHero(), battle.getMonster());
        actualTurn.setDamageDealt(damageDealt);

        if (battle.getMonster().getLifePoints() <= 0) {
            battle.setStatus(BattleStatus.VICTORY);
        }

        actualTurn = this.turnService.updateTurn(actualTurn);
        battle = this.battleRepository.save(battle);

        return new BattleStatusResponse(actualTurn, battle.getHero(), battle.getMonster(), "Rapido, defenda-se!");
    }

    @Override
    public BattleStatusResponse defend(InteractInBattleDto dto) {
        var battle = this.battleHistoryService.getBattleById(dto.id());
        var actualTurn = this.turnService.getActualTurn(battle);

        this.doTurnValidations(new ValidateEndGame(battle), new ValidatePlayerCanDefend(actualTurn));

        var damageReceived = this.attack(battle.getMonster(), battle.getHero());
        actualTurn.setDamageReceived(damageReceived);

        if (battle.getHero().getLifePoints() <= 0) {
            battle.setStatus(BattleStatus.DEFEATED);
        }

        actualTurn = this.turnService.updateTurn(actualTurn);
        battle = this.battleRepository.save(battle);

        return new BattleStatusResponse(actualTurn, battle.getHero(), battle.getMonster(), "Sua vez de atacar");
    }

    private void doTurnValidations(ValidationTurn... validation) {
        for (ValidationTurn validationTurn : validation) {
            validationTurn.validate();
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
