package br.com.berdnaski.heroes.controllers;

import br.com.berdnaski.heroes.dto.AttackRequestDTO;
import br.com.berdnaski.heroes.dto.HeroRequestDTO;
import br.com.berdnaski.heroes.dto.HeroResponseDTO;
import br.com.berdnaski.heroes.dto.MessageResponseDTO;
import br.com.berdnaski.heroes.services.HeroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
@RequiredArgsConstructor
@CrossOrigin(origins = "${app.cors.allowed-origins}")
public class HeroController {
    private final HeroService heroService;

    @PostMapping
    public ResponseEntity<HeroResponseDTO> create(@Valid @RequestBody HeroRequestDTO req) {
        HeroResponseDTO res = heroService.create(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<HeroResponseDTO>> getHeroes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer attackPower,
            @RequestParam(required = false) Integer defensePower) {

        List<HeroResponseDTO> res = heroService.getHeroes(name, attackPower, defensePower);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}/attack")
    public ResponseEntity<MessageResponseDTO> attack(
            @PathVariable String id,
            @Valid @RequestBody AttackRequestDTO req) {

        try {
            MessageResponseDTO res = heroService.attackHero(id, req);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponseDTO("O herói não está no campo de batalha."));
        }
    }
}
