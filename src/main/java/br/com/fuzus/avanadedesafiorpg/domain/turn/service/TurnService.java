package br.com.fuzus.avanadedesafiorpg.domain.turn.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;

public interface TurnService {
    Turn getActualTurn(Battle battle);
    Turn updateTurn(Turn turn);

    Turn createTurn(Turn turn, Battle battle);
}
