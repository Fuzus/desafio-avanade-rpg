package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.BattleStatus;
import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateEndGame implements ValidationTurn{

    private final Battle battle;
    private final String errorMessage;

    @Override
    public void validate() {
        if (battle.getStatus() == BattleStatus.VICTORY || battle.getStatus() == BattleStatus.DEFEATED) {
            throw new UnableToMoveException(errorMessage);
        }
    }
}
