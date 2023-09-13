package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Subject;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidatePlayerCanDefend implements ValidationTurn {

    private final Turn turn;

    @Override
    public void validate() {
        boolean hasToDefend = (turn.getInitiativeWinner() == Subject.MONSTER && turn.getDamageReceived() == null )
                                    || turn.getDamageDealt() != null;
        if (!hasToDefend) {
            throw new UnableToMoveException("Personagem não deve defender neste turno");
        }
    }
}
