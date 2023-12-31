package br.com.fuzus.avanadedesafiorpg.domain.character.repository;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterRepository extends JpaRepository<Character, UUID> {
}
