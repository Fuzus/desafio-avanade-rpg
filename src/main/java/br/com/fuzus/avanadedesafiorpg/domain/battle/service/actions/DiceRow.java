package br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;

import java.util.Random;

public class DiceRow {

    public static int diceRow(Dice dice){
        Random random = new Random();
        return random.nextInt(1, dice.faces);
    }

}
