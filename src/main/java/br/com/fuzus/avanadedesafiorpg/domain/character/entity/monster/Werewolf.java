package br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

public class Werewolf extends Character {
    public Werewolf(String name) {
        super(null, name, 34, 7, 4, 7, 2, Dice.D4);
    }
}