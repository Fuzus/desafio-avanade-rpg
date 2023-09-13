package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations.ValidationTurn;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;

public class ValidatePlayerTurnAttack implements ValidationTurn {
    @Override
    public void validate(Battle battle, Turn turn) {
        boolean hasToAttack = turn.getDamageDealt() == null;
        boolean canAttack = battle.getPlayerInitiative() > battle.getMonsterInitiative() || turn.getDamageReceived() != null;
        if (!(hasToAttack && canAttack)) {
            throw new UnableToMoveException("Personagem não deve atacar neste turno");
        }
    }
}
