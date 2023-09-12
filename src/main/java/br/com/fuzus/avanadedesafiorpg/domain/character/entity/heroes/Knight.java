package br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public class Knight extends Character {

    public Knight(String name){
        super(null, name,  26, 6, 8, 3, 2, Dice.D6);
    }
}
