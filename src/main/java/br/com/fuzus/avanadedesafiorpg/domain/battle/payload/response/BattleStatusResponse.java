package br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.response.CharacterDtoResponse;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;

public record BattleStatusResponse(
        Long turnNumber,
        Integer damageDealt,
        Integer damageReceived,
        CharacterDtoResponse heroStatus,
        CharacterDtoResponse monsterStatus,
        String message
) {
    public BattleStatusResponse(Turn turn, Character hero, Character monster, String message) {
        this(
                turn.getTurnNumber(),
                turn.getDamageDealt(),
                turn.getDamageReceived(),
                new CharacterDtoResponse(hero),
                new CharacterDtoResponse(monster),
                message
        );
    }
}
