package br.com.fuzus.avanadedesafiorpg.domain.turn.service;

import br.com.fuzus.avanadedesafiorpg.domain.battle.entity.Battle;
import br.com.fuzus.avanadedesafiorpg.domain.turn.entity.Turn;
import br.com.fuzus.avanadedesafiorpg.domain.turn.repository.TurnRepository;
import br.com.fuzus.avanadedesafiorpg.domain.turn.service.validators.ValidateActionsAvailable;
import br.com.fuzus.avanadedesafiorpg.domain.turn.service.validators.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurnServiceImp implements TurnService{

    private final TurnRepository repository;


    @Override
    @Transactional
    public Turn getActualTurn(Battle battle) {
        var turn = this.repository.findTopByBattleOrderByIdDesc(battle);
        Validator validator = new ValidateActionsAvailable(turn);
        if (validator.validate())
            return turn;
        var newTurn = new Turn(null, turn.getTurnNumber() + 1, null, null, battle, turn.getInitiativeWinner());
        return this.createTurn(newTurn, battle);
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
