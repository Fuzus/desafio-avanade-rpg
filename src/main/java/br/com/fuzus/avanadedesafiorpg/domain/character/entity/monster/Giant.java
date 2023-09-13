package br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.CharacterClassConst;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(CharacterClassConst.GIANT)
@NoArgsConstructor
public class Giant extends Character {
    public Giant(String name){
        super(null, name, 34, 10, 4, 4,2, Dice.D6, CharacterClassConst.GIANT);
    }
}
