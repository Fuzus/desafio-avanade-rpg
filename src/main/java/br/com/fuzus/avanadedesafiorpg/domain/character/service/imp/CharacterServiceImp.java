package br.com.fuzus.avanadedesafiorpg.domain.character.service.imp;

import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes.Barbarian;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes.Knight;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.heroes.Warrior;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster.Giant;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster.Orc;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.monster.Werewolf;
import br.com.fuzus.avanadedesafiorpg.domain.character.payload.request.CreateCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.repository.CharacterRepository;
import br.com.fuzus.avanadedesafiorpg.domain.character.service.CharacterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterServiceImp  implements CharacterService {

    private final CharacterRepository repository;
    private final Environment environment;

    @Override
    public Character createWarrior(CreateCharacterDto dto) {
        return this.saveInDatabase(new Warrior(dto.name()));
    }

    @Override
    public Character createKnight(CreateCharacterDto dto) {
        return this.saveInDatabase(new Knight(dto.name()));
    }

    @Override
    public Character createBarbarian(CreateCharacterDto dto) {
        return this.saveInDatabase(new Barbarian(dto.name()));
    }

    @Override
    public Character createGiant(CreateCharacterDto dto) {
        return this.saveInDatabase(new Giant(dto.name()));
    }

    @Override
    public Character createWerewolf(CreateCharacterDto dto) {
        return this.saveInDatabase(new Werewolf(dto.name()));
    }

    @Override
    public Character createOrc(CreateCharacterDto dto) {
        return this.saveInDatabase(new Orc(dto.name()));
    }

    @Override
    public Character getById(String id) {
        var uuid = UUID.fromString(id);
        return repository.findById(uuid).orElseThrow(() -> new EntityNotFoundException(environment.getProperty("character.notFound")));
    }

    @Override
    public Character update(String id, CreateCharacterDto dto) {
        Character character = getById(id);
        character.setName(dto.name());
        return this.saveInDatabase(character);
    }

    @Override
    public void delete(String id) {
        var uuid = UUID.fromString(id);
        repository.deleteById(uuid);
    }

    @Override
    public List<Character> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Character createRandomMonster(int i) {
        Character monster = null;
        switch (i) {
            case 1 -> monster = new Orc(environment.getProperty("character.orc.defaultName"));
            case 2 -> monster = new Giant(environment.getProperty("character.giant.defaultName"));
            case 3 -> monster = new Werewolf(environment.getProperty("character.werewolf.defaultName"));
        }
        return this.saveInDatabase(monster);
    }

    private Character saveInDatabase(Character character){
        return repository.save(character);
    }
}
