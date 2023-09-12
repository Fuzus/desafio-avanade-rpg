package br.com.fuzus.avanadedesafiorpg.cotroller;

import br.com.fuzus.avanadedesafiorpg.domain.character.dto.CreateCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.dto.ResponseCharacterDto;
import br.com.fuzus.avanadedesafiorpg.domain.character.entity.Character;
import br.com.fuzus.avanadedesafiorpg.domain.character.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("personagem")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService service;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCharacterDto> getCharacter(@PathVariable String id){
        var character = this.service.getById(id);
        return ResponseEntity.ok(new ResponseCharacterDto(character));
    }

    @PostMapping("/guerreiro")
    public ResponseEntity<ResponseCharacterDto> createWarrior(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var warrior = this.service.createWarrior(dto);
        return this.createCharacterResponse(warrior, uriBuilder);
    }

    @PostMapping("/barbaro")
    public ResponseEntity<ResponseCharacterDto> createBarbarian(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var barbarian = this.service.createBarbarian(dto);
        return this.createCharacterResponse(barbarian, uriBuilder);
    }

    @PostMapping("/cavaleiro")
    public ResponseEntity<ResponseCharacterDto> createKnight(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var knight = this.service.createKnight(dto);
        return this.createCharacterResponse(knight, uriBuilder);
    }

    @PostMapping("/orc")
    public ResponseEntity<ResponseCharacterDto> createOrc(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var orc = this.service.createOrc(dto);
        return this.createCharacterResponse(orc, uriBuilder);
    }

    @PostMapping("/gigante")
    public ResponseEntity<ResponseCharacterDto> createGiant(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var giant = this.service.createGiant(dto);
        return this.createCharacterResponse(giant, uriBuilder);
    }

    @PostMapping("/lobisomem")
    public ResponseEntity<ResponseCharacterDto> createWereWolf(@RequestBody CreateCharacterDto dto, UriComponentsBuilder uriBuilder) {
        var werewolf = this.service.createWerewolf(dto);
        return this.createCharacterResponse(werewolf, uriBuilder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCharacterDto> updateCharacter(@PathVariable String id, @RequestBody CreateCharacterDto dto){
        var character = this.service.update(id, dto);
        return ResponseEntity.ok(new ResponseCharacterDto(character));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<ResponseCharacterDto> createCharacterResponse(Character character, UriComponentsBuilder uriBuilder) {
        var uri = uriBuilder.path("/personagem/{id}").buildAndExpand(character.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCharacterDto(character));
    }

}
