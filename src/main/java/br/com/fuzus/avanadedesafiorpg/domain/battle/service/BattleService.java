package br.com.fuzus.avanadedesafiorpg.domain.battle.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.InteractInBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.StartBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleInitiativeResultResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStartedResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStatusResponse;

public interface BattleService {

    BattleStartedResponse startBattle(StartBattleDto dto);

    BattleInitiativeResultResponse diceInitiative(InteractInBattleDto id);

    BattleStatusResponse attack(InteractInBattleDto dto);

    BattleStatusResponse defend(InteractInBattleDto dto);
}
