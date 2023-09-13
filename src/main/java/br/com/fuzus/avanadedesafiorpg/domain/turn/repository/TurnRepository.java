package br.com.fuzus.avanadedesafiorpg.domain.turn.repository;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnRepository extends JpaRepository<Turn, Long> {

    Turn findTopByBattleOrderByIdDesc(Battle battle);

}
