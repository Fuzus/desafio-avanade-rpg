package br.com.fuzus.avanadedesafiorpg.domain.battle.entity;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hero_id")
    private Character hero;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "monster_id")
    private Character monster;
    private Integer playerInitiative;
    private Integer monsterInitiative;
    @OneToMany(mappedBy = "battle", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Turn> turns;
    @Enumerated(EnumType.STRING)
    private BattleStatus status;

    public Battle(Character hero, Character monster) {
        this.hero = hero;
        this.monster = monster;
        this.status = BattleStatus.STARTED;
    }
}
