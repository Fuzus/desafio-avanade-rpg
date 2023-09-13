package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations.ValidationTurn;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Subject;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;

public class ValidatePlayerTurnDefence implements ValidationTurn {

    @Override
    public void validate(Battle battle, Turn turn) {
        boolean hasToDefend = (turn.getInitiativeWinner() == Subject.MONSTER && turn.getDamageReceived() == null )
                                    || turn.getDamageDealt() != null;
        if (!hasToDefend) {
            throw new UnableToMoveException("Personagem não deve defender neste turno");
        }
    }
}
