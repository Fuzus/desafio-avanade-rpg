package br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.CharacterClassConst;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(CharacterClassConst.BARBARIAN)
@NoArgsConstructor
public class Barbarian extends Character {

    public Barbarian(String name) {
        super(null, name, 21, 10, 2, 5, 2, Dice.D8, CharacterClassConst.BARBARIAN);
    }
}
