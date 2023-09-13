package br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request;

import jakarta.validation.constraints.NotNull;

public record InteractInBattleDto(
        @NotNull
        Long id
) {
}
