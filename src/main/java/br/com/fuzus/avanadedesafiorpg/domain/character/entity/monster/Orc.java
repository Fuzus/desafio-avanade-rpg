package br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public class Orc extends Character {
    public Orc(String name) {
        super(null, name, 42, 7, 1, 2, 3, Dice.D4);
    }
}