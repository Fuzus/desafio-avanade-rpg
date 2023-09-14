package br.com.fuzus.avanadedesafiorpg.domain.turn.entity;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;

    private Long turnNumber;

    private Integer damageDealt;

    private Integer damageReceived;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "battle_id")
    @JsonBackReference
    private Battle battle;

    @Enumerated(EnumType.STRING)
    private Subject initiativeWinner;

    public Turn(Long turnNumber) {
        this.turnNumber = turnNumber;
    }
}
