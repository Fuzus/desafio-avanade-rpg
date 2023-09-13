package br.com.fuzus.avanadedesafiorpg.domain.turn.entity;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long turnNumber;

    private Integer damageDealt;

    private Integer damageReceived;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    public Turn(Long turnNumber) {
        this.turnNumber = turnNumber;
    }
}
