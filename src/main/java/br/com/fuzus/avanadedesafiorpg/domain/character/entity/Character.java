package br.com.fuzus.avanadedesafiorpg.domain.character.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Integer lifePoints;
    private Integer strength;
    private Integer defence;
    private Integer agility;
    private Integer diceNumber;

    @Enumerated(EnumType.STRING)
    private Dice diceType;
}
