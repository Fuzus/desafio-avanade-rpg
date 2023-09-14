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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("batalhar")
@RequiredArgsConstructor
public class BattleController {

    private final BattleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<BattleStartedResponse> startBattle(@Valid @RequestBody StartBattleDto dto, UriComponentsBuilder uriBuilder){
        var startedBattle = this.service.startBattle(dto);
        var uri = uriBuilder.path("battle/{id}").buildAndExpand(startedBattle).toUri();
        return ResponseEntity.created(uri).body(startedBattle);
    }

    @PatchMapping("/iniciativa")
    @Transactional
    public ResponseEntity<BattleInitiativeResultResponse> rollInitiative(@Valid @RequestBody InteractInBattleDto dto){
        var battleStatus = this.service.diceInitiative(dto);
        return ResponseEntity.ok(battleStatus);
    }

    @PatchMapping("/atacar")
    @Transactional
    public ResponseEntity<BattleStatusResponse> rollAttack(@Valid @RequestBody InteractInBattleDto dto){
        var battleStatus = this.service.attack(dto);
        return ResponseEntity.ok(battleStatus);
    }

    @PatchMapping("/defender")
    @Transactional
    public ResponseEntity<BattleStatusResponse> rollDefence(@Valid @RequestBody InteractInBattleDto dto){
        var battleStatus = this.service.defend(dto);
        return ResponseEntity.ok(battleStatus);
    }

}
