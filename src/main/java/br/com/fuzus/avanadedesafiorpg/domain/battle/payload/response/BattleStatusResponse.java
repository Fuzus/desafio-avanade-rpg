package br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.response.CharacterDtoResponse;

public record BattleStatusResponse(
        Integer turnNumber,
        Integer damageDealt,
        Integer damageReceived,
        CharacterDtoResponse heroStatus,
        CharacterDtoResponse monsterStatus,
        String message
) {
    public BattleStatusResponse(Integer turnNumber, Battle battle, String message) {
        this(turnNumber,
                null,
                null,
                new CharacterDtoResponse(battle.getHero()),
                new CharacterDtoResponse(battle.getMonster()),
                message);
    }
}
