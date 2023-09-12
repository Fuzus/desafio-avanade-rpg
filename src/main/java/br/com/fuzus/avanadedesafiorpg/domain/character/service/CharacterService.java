package br.com.fuzus.avanadedesafiorpg.domain.character.service;

import br.com.fuzus.avanadedesafiorpg.domain.character.dto.CreateCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;

public interface CharacterService {

    Character createWarrior(CreateCharacterDto dto);
    Character createKnight(CreateCharacterDto dto);
    Character createBarbarian(CreateCharacterDto dto);
    Character createGiant(CreateCharacterDto dto);
    Character createWerewolf(CreateCharacterDto dto);
    Character createOrc(CreateCharacterDto dto);
    Character getById(String id);
    Character update(String id, CreateCharacterDto dto);
    void delete(String id);
}
