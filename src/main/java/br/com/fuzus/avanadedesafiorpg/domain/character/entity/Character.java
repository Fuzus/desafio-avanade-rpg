package br.com.fuzus.avanadedesafiorpg.domain.character.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
public abstract class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer lifePoints;
    private Integer strength;
    private Integer defence;
    private Integer agility;
    private Integer diceNumber;

    @Enumerated(EnumType.STRING)
    private Dice diceType;
}
