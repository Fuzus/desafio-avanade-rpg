package br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public class Barbarian extends Character {

    public Barbarian(String name) {
        super(null, name, 21,10,2,5,2, Dice.D8);
    }
}
