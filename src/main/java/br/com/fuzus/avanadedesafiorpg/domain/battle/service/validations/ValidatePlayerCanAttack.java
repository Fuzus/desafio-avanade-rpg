package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Subject;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;

public class ValidatePlayerCanAttack implements ValidationTurn {
    @Override
    public void validate(Battle battle, Turn turn) {
        boolean canAttack = (turn.getInitiativeWinner() == Subject.HERO && turn.getDamageDealt() == null)
                                    || turn.getDamageReceived() != null;
        if (!canAttack) {
            throw new UnableToMoveException("Personagem não deve atacar neste turno");
        }
    }
}
