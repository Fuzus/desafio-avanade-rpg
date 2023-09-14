package br.com.fuzus.avanadedesafiorpg.domain.character.payload.response;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public record ClassDataResponse(
        String characterClass,
        Integer lifePoints,
        Integer strength,
        Integer defence,
        Integer agility,
        Integer diceNumber,
        Dice diceType
) {

    public ClassDataResponse(Character character) {
        this(
                character.getCharacterClass(),
                character.getLifePoints(),
                character.getStrength(),
                character.getDefence(),
                character.getAgility(),
                character.getDiceNumber(),
                character.getDiceType()
        );
    }

}
