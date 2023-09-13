package br.com.fuzus.avanadedesafiorpg.domain.battle.service.imp;

import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleInitiativeResultResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStartedResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.StartBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Turn;
import br.com.fuzus.avanadedesafiorpg.domain.battle.repository.BattleRepository;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.BattleService;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.DiceRow;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.request.CreateCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import br.com.fuzus.avanadedesafiorpg.domain.character.service.CharacterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattleServiceImp implements BattleService {

    private final BattleRepository repository;
    private final CharacterService characterService;

    @Override
    public BattleStartedResponse startBattle(StartBattleDto dto) {
        var hero = this.characterService.getById(dto.chosenHero());
        Character monster;
        if (dto.chosenMonster() != null) {
            monster = this.characterService.getById(dto.chosenMonster());
        } else {
            monster = this.characterService.createOrc(new CreateCharacterDto("josnei"));
        }
        var battle = new Battle(hero, monster);
        battle = this.repository.save(battle);
        return new BattleStartedResponse(battle, "Batalha iniciada");
    }

    @Override
    public BattleInitiativeResultResponse diceInitiative(Long id) {
        var battle = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Batalha não encontrada"));
        String nextAttacker = "";
        do {
            var playerInitiative = DiceRow.diceRow(Dice.D20);
            var monsterInitiative = DiceRow.diceRow(Dice.D20);

            battle.setPlayerInitiative(playerInitiative);
            battle.setMonsterInitiative(monsterInitiative);
            nextAttacker = playerInitiative > monsterInitiative ? "Jogador" : "Monstro";

        } while (battle.getPlayerInitiative().equals(battle.getMonsterInitiative()));
        battle = this.repository.save(battle);
        return new BattleInitiativeResultResponse(battle, String.format("%s inicia atacando", nextAttacker));
    }

    @Override
    public Turn attack() {
        return null;
    }

    @Override
    public Turn defend() {
        return null;
    }

    @Override
    public void run() {

    }
}
