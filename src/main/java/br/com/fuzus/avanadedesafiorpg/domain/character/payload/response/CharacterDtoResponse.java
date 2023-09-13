package br.com.fuzus.avanadedesafiorpg.domain.character.dto;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ResponseCharacterDto(
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

    public ResponseCharacterDto(Character character) {
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
