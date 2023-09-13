package br.com.fuzus.avanadedesafiorpg.domain.turn.service.validators;

import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActionsAvaliableInTurn implements Validator{

    private final Turn turn;

    @Override
    public boolean validate() {
        return turn.getDamageDealt() != null || turn.getDamageReceived() != null;
    }
}
