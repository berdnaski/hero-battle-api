package br.com.berdnaski.heroes.services;

import br.com.berdnaski.heroes.domain.hero.Hero;
import br.com.berdnaski.heroes.domain.hero.specification.HeroSpecification;
import br.com.berdnaski.heroes.dto.AttackRequestDTO;
import br.com.berdnaski.heroes.dto.HeroRequestDTO;
import br.com.berdnaski.heroes.dto.HeroResponseDTO;
import br.com.berdnaski.heroes.dto.MessageResponseDTO;
import br.com.berdnaski.heroes.repositories.HeroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroService {
    private final HeroRepository heroRepository;

    public HeroResponseDTO create(HeroRequestDTO req) {
        Hero hero = new Hero();
        hero.setName(req.getName());
        hero.setAttackPower(req.getAttackPower());
        hero.setDefensePower(req.getDefensePower());
        hero.setHealth(req.getHealth());

        Hero saved = heroRepository.save(hero);
        return toResponseDTO(saved);
    }

    public List<HeroResponseDTO> getHeroes(String name, Integer attackPower, Integer defensePower) {
        Specification<Hero> spec = Specification.allOf(
                name != null && !name.isEmpty() ? HeroSpecification.withName(name) : null,
                attackPower != null ? HeroSpecification.withAttackPower(attackPower) : null,
                defensePower != null ? HeroSpecification.withDefensePower(defensePower) : null
        );

        return heroRepository.findAll(spec).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO attackHero(String id, AttackRequestDTO req) {
        Hero hero = heroRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("O herói não está no campo de batalha."));

        if (req.getAttackValue() <= hero.getDefensePower()) {
            return new MessageResponseDTO("O herói não sofreu danos, tente novamente.");
        }

        int damage = req.getAttackValue() - hero.getDefensePower();
        int newHealth = hero.getHealth() - damage;

        if (newHealth < 0) {
            newHealth = 0;
        }

        hero.setHealth(newHealth);
        heroRepository.save(hero);

        return new MessageResponseDTO(
                String.format("O herói sofreu danos. Vida atual: %d", newHealth)
        );
    }

    private HeroResponseDTO toResponseDTO(Hero hero) {
        return new HeroResponseDTO(
                hero.getId(),
                hero.getName(),
                hero.getAttackPower(),
                hero.getDefensePower(),
                hero.getHealth()
        );
    }
}
