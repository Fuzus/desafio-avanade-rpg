package br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.dice;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

import java.util.Random;

public class DiceRoll {

    public static int diceRoll(Dice dice){
        Random random = new Random();
        return random.nextInt(1, dice.faces + 1);
    }

}
