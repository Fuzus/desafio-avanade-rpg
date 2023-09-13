package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations.ValidationTurn;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;

public class ValidatePlayerTurnDefence implements ValidationTurn {

    @Override
    public void validate(Battle battle, Turn turn) {
        boolean canDefend = turn.getDamageReceived() == null;
        boolean hasToDefend = battle.getPlayerInitiative() < battle.getMonsterInitiative() || turn.getDamageDealt() != null;
        if (!(canDefend && hasToDefend)) {
            throw new UnableToMoveException("Personagem não deve defender neste turno");
        }
    }
}
