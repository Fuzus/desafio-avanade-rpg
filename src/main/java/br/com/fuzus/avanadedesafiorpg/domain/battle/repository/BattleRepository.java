package br.com.fuzus.avanadedesafiorpg.domain.battle.repository;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleRepository extends JpaRepository<Battle, Long> {
}
