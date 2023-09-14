package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Subject;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidatePlayerCanAttack implements ValidationTurn {

    private final Turn turn;
    private final String errorMessage;

    @Override
    public void validate() {
        boolean canAttack = (turn.getInitiativeWinner() == Subject.HERO && turn.getDamageDealt() == null)
                                    || turn.getDamageReceived() != null;
        if (!canAttack) {
            throw new UnableToMoveException(errorMessage);
        }
    }
}
