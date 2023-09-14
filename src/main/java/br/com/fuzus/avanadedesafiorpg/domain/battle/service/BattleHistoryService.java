package br.com.fuzus.avanadedesafiorpg.domain.battle.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BattleHistoryService {

    Page<Battle> getAll(Pageable page);

    Battle getBattleById(Long id);

}
