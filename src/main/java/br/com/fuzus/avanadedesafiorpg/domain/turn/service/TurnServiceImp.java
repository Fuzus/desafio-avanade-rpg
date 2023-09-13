package br.com.fuzus.avanadedesafiorpg.domain.turn.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import br.com.fuzus.avanadedesafiorpg.domain.turn.repository.TurnRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurnServiceImp implements TurnService{

    private final TurnRepository repository;


    @Override
    public Turn getActualTurn(Battle battle) {
        return this.repository.findTopByBattleOrderByIdDesc(battle);
    }

    @Override
    @Transactional
    public Turn updateTurn(Turn turn) {
        return this.repository.save(turn);
    }

    @Override
    @Transactional
    public Turn createTurn(Turn turn, Battle battle) {
        turn.setBattle(battle);
        return this.repository.save(turn);
    }
}
