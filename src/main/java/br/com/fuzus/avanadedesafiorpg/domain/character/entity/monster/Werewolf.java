package br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.CharacterClassConst;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(CharacterClassConst.WEREWOLF)
@NoArgsConstructor
public class Werewolf extends Character {
    public Werewolf(String name) {
        super(null, name, 34, 7, 4, 7, 2, Dice.D4, CharacterClassConst.WEREWOLF);
    }
}
