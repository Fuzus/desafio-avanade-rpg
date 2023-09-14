package br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.BattleStatus;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.response.CharacterDtoResponse;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;

import java.util.List;

public record BattleHistoryResponse(
        Long id,
        CharacterDtoResponse hero,
        CharacterDtoResponse monster,
        Integer playerInitiative,
        Integer monsterInitiative,
        BattleStatus status,
        List<Turn> turns
) {
    public BattleHistoryResponse(Battle battle) {
        this(
                battle.getId(),
                new CharacterDtoResponse(battle.getHero()),
                new CharacterDtoResponse(battle.getMonster()),
                battle.getPlayerInitiative(),
                battle.getMonsterInitiative(),
                battle.getStatus(),
                battle.getTurns()
        );
    }
}
