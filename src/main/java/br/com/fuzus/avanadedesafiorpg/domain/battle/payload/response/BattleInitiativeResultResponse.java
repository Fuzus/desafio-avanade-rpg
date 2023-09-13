package br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.response.CharacterDtoResponse;

public record BattleInitiativeResultResponse(
        Long battleId,
        Integer resultPlayerInitiative,
        Integer resultMonsterInitiative,
        CharacterDtoResponse player,
        CharacterDtoResponse monster,
        String message
) {
    public BattleInitiativeResultResponse(Battle battle, String message){
        this(
                battle.getId(),
                battle.getPlayerInitiative(),
                battle.getMonsterInitiative(),
                new CharacterDtoResponse(battle.getHero()),
                new CharacterDtoResponse(battle.getMonster()),
                message
        );
    }

}
