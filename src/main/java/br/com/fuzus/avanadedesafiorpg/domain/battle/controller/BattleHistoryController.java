package br.com.fuzus.avanadedesafiorpg.domain.battle.controller;

import br.com.fuzus.avanadedesafiorpg.domain.battle.payload.response.BattleHistoryResponse;
import br.com.fuzus.avanadedesafiorpg.domain.battle.service.BattleHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("historico")
@RequiredArgsConstructor
public class BattleHistoryController {

    private final BattleHistoryService service;

    @GetMapping
    public ResponseEntity<Page<BattleHistoryResponse>> getAllHistory(
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
            Pageable page){
        var battlesHistory = this.service.getAll(page).map(BattleHistoryResponse::new);
        return ResponseEntity.ok(battlesHistory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BattleHistoryResponse> getBattleById(@PathVariable Long id) {
        var battle = this.service.getBattleById(id);
        return ResponseEntity.ok(new BattleHistoryResponse(battle));
    }
}
