package br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.CharacterClassConst;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(CharacterClassConst.WARRIOR)
@NoArgsConstructor
public class Warrior extends Character {

    public Warrior(String name){
        super(null, name, 20, 7, 5, 6, 1, Dice.D12, CharacterClassConst.WARRIOR);
    }
}
