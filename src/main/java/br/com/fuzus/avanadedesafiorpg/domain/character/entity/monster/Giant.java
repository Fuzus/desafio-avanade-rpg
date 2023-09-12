package br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public class Giant extends Character {
    public Giant(String name){
        super(null, name, 34, 10, 4, 4,2, Dice.D6);
    }
}
