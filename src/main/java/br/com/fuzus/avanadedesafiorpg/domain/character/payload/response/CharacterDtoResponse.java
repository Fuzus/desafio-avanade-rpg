package br.com.fuzus.avanadedesafiorpg.domain.character.payload.response;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public record CharacterDtoResponse(
        String id,
        String name,
        Integer lifePoints,
        Integer strength,
        Integer defence,
        Integer agility,
        Integer diceNumber,
        Dice diceType,
        String characterClass
) {

    public CharacterDtoResponse(Character character) {
        this(
                character.getId().toString(),
                character.getName(),
                character.getLifePoints(),
                character.getStrength(),
                character.getDefence(),
                character.getAgility(),
                character.getDiceNumber(),
                character.getDiceType(),
                character.getCharacterClass()
        );
    }

}
