package br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions;

import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.dice.DiceRoll;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;

public class CalculateDamage implements DamageActions {

    private final Character character;

    public CalculateDamage(Character character) {
        this.character = character;
    }

    @Override
    public int perform() {
        int totalDamage = 0;
        for (int i = 0; i < character.getDiceNumber(); i++){
            totalDamage += DiceRoll.diceRoll(character.getDiceType());
        }
        totalDamage += character.getStrength();
        return totalDamage;
    }
}
