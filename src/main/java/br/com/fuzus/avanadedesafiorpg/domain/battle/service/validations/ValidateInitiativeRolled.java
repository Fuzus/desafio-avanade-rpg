package br.com.fuzus.avanadedesafiorpg.domain.battle.service.validations;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateInitiativeRolled implements ValidationTurn{

    private final Battle battle;

    @Override
    public void validate() {
        if (battle.getPlayerInitiative() != null && battle.getMonsterInitiative() != null){
            throw new UnableToMoveException("Iniciativa ja foi rolada");
        }
    }
}
