package br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public class Warrior extends Character {

    public Warrior(String name){
        super(null, name, 20, 7, 5, 6, 1, Dice.D12);
    }
}
