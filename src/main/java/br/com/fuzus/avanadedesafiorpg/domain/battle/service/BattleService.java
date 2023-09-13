package br.com.fuzus.avanadedesafiorpg.domain.battle.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleInitiativeResultResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStartedResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.StartBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Turn;

public interface BattleService {

    BattleStartedResponse startBattle(StartBattleDto dto);

    BattleInitiativeResultResponse diceInitiative(Long id);

    Turn attack();

    Turn defend();

    void run();
}
