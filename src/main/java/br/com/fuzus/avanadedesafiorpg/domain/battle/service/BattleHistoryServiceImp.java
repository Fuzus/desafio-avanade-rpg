package br.com.fuzus.avanadedesafiorpg.domain.battle.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.battle.repository.BattleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BattleHistoryServiceImp implements BattleHistoryService{

    private final BattleRepository repository;
    private final Environment environment;

    @Override
    public Page<Battle> getAll(Pageable page) {
        return this.repository.findAll(page);
    }

    @Override
    public Battle getBattleById(Long id) {
       return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException(environment.getProperty("battle.notFound")));
    }
}
