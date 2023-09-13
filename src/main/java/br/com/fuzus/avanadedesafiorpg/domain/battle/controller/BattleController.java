package br.com.fuzus.avanadedesafiorpg.domain.battle.controller;

import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.InteractInBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request.StartBattleDto;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleInitiativeResultResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStartedResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleStatusResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.BattleService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("batalhar")
@RequiredArgsConstructor
public class BattleController {

    private final BattleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<BattleStartedResponse> startBattle(@Valid @RequestBody StartBattleDto dto){
        var startedBattle = this.service.startBattle(dto);
        return ResponseEntity.ok(startedBattle);
    }

    @PostMapping("/iniciativa")
    @Transactional
    public ResponseEntity<BattleInitiativeResultResponse> rowInitiative(@Valid @RequestBody InteractInBattleDto dto){
        var battleStatus = this.service.diceInitiative(dto);
        return ResponseEntity.ok(battleStatus);
    }

    @PostMapping("/atacar")
    @Transactional
    public ResponseEntity<BattleStatusResponse> rowAttack(@Valid @RequestBody InteractInBattleDto dto){
        var battleStatus = this.service.attack(dto);
        return ResponseEntity.ok(battleStatus);
    }

    @PostMapping("/defender")
    @Transactional
    public ResponseEntity<BattleStatusResponse> rowDefence(@Valid @RequestBody InteractInBattleDto dto){
        var battleStatus = this.service.defend(dto);
        return ResponseEntity.ok(battleStatus);
    }

}
