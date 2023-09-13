package br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.response.CharacterDtoResponse;

public record BattleStartedResponse(
        Long id,
        CharacterDtoResponse player,
        CharacterDtoResponse monster,
        String message
) {
    public BattleStartedResponse(Battle battle, String message){
        this(battle.getId(), new CharacterDtoResponse(battle.getHero()), new CharacterDtoResponse(battle.getMonster()), message);
    }
}
