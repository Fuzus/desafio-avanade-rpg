package br.com.fuzus.avanadedesafiorpg.domain.character.cotroller;

import br.com.fuzus.avanadedesafiorpg.domain.character.dto.CreateCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.dto.ResponseCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.service.CharacterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("personagens")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService service;

    @GetMapping
    public ResponseEntity<List<ResponseCharacterDto>> getAllCharacters(){
        var characters = this.service.getAll();
        return ResponseEntity.ok(characters.stream().map(ResponseCharacterDto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCharacterDto> getCharacter(@PathVariable String id){
        var character = this.service.getById(id);
        return ResponseEntity.ok(new ResponseCharacterDto(character));
    }

    @PostMapping("/guerreiro")
    @Transactional
    public ResponseEntity<ResponseCharacterDto> createWarrior(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var warrior = this.service.createWarrior(dto);
        return this.createCharacterResponse(warrior,  uriBuilder);
    }

    @PostMapping("/barbaro")
    @Transactional
    public ResponseEntity<ResponseCharacterDto> createBarbarian(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var barbarian = this.service.createBarbarian(dto);
        return this.createCharacterResponse(barbarian, uriBuilder);
    }

    @PostMapping("/cavaleiro")
    @Transactional
    public ResponseEntity<ResponseCharacterDto> createKnight(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var knight = this.service.createKnight(dto);
        return this.createCharacterResponse(knight, uriBuilder);
    }

    @PostMapping("/orc")
    @Transactional
    public ResponseEntity<ResponseCharacterDto> createOrc(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var orc = this.service.createOrc(dto);
        return this.createCharacterResponse(orc, uriBuilder);
    }

    @PostMapping("/gigante")
    @Transactional
    public ResponseEntity<ResponseCharacterDto> createGiant(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var giant = this.service.createGiant(dto);
        return this.createCharacterResponse(giant, uriBuilder);
    }

    @PostMapping("/lobisomem")
    @Transactional
    public ResponseEntity<ResponseCharacterDto> createWereWolf(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var werewolf = this.service.createWerewolf(dto);
        return this.createCharacterResponse(werewolf, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseCharacterDto> updateCharacter(@PathVariable String id, @RequestBody CreateCharacterDto dto){
        var character = this.service.update(id, dto);
        return ResponseEntity.ok(new ResponseCharacterDto(character));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCharacter(@PathVariable String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ResponseCharacterDto> createCharacterResponse(Character character, UriComponentsBuilder uriBuilder) {
        var uri = uriBuilder.path("/personagem/{id}").buildAndExpand(character.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCharacterDto(character));
    }

}
