package br.com.fuzus.avanadedesafiorpg.domain.character.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Character {
    private String id;
    private String name;
    private Integer lifePoints;
    private Integer strength;
    private Integer defence;
    private Integer agility;
    private Integer diceNumber;
    private Dice diceFaces;
}
