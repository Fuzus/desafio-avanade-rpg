package br.com.fuzus.avanadedesafiorpg.domain.character.payload.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCharacterDto(
        @NotBlank
        String name
) {
}
