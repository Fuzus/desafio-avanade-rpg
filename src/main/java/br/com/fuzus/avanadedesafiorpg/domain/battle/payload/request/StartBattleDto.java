package br.com.fuzus.avanadedesafiorpg.domain.battle.payload.request;

import jakarta.validation.constraints.NotBlank;

public record StartBattleDto(
        @NotBlank
        String chosenHero,
        String chosenMonster
) {
}
