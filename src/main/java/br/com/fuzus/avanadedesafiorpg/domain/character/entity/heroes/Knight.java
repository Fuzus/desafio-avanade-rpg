package br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.CharacterClassConst;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(CharacterClassConst.KNIGHT)
@NoArgsConstructor
public class Knight extends Character {

    public Knight(String name){
        super(null, name,  26, 6, 8, 3, 2, Dice.D6, CharacterClassConst.KNIGHT);
    }
}
