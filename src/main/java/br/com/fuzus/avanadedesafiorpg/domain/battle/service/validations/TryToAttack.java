package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.service.actions.dice.DiceRoll;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Dice;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TryToAttack implements AttackValidations {

    private final Character attacker;
    private final Character target;

    @Override
    public boolean validate() {
        return getValueAttacker() - getValueTarget() > 0;
    }

    private int getValueAttacker(){
        return DiceRoll.diceRoll(Dice.D12) + attacker.getStrength() + attacker.getAgility();
    }

    private int getValueTarget() {
        return DiceRoll.diceRoll(Dice.D12) + target.getDefence() + target.getAgility();
    }
}
