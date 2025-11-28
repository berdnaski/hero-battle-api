package br.com.berdnaski.heroes.services;

import br.com.berdnaski.heroes.domain.hero.Hero;
import br.com.berdnaski.heroes.dto.AttackRequestDTO;
import br.com.berdnaski.heroes.dto.HeroRequestDTO;
import br.com.berdnaski.heroes.dto.HeroResponseDTO;
import br.com.berdnaski.heroes.dto.MessageResponseDTO;
import br.com.berdnaski.heroes.repositories.HeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @InjectMocks
    private HeroService heroService;

    @Test
    void create_shouldPersistAndMap() {
        HeroRequestDTO req = new HeroRequestDTO("Thor", 80, 60, 100);
        Hero saved = new Hero("1", "Thor", 80, 60, 100);
        when(heroRepository.save(any(Hero.class))).thenReturn(saved);

        HeroResponseDTO res = heroService.create(req);

        assertNotNull(res);
        assertEquals("1", res.getId());
        assertEquals("Thor", res.getName());
        assertEquals(80, res.getAttackPower());
        assertEquals(60, res.getDefensePower());
        assertEquals(100, res.getHealth());
        verify(heroRepository, times(1)).save(any(Hero.class));
    }

    @Test
    void getById_shouldReturnMapped() {
        Hero hero = new Hero("abc", "Hulk", 70, 50, 100);
        when(heroRepository.findById("abc")).thenReturn(Optional.of(hero));

        HeroResponseDTO res = heroService.getById("abc");

        assertEquals("abc", res.getId());
        assertEquals("Hulk", res.getName());
    }

    @Test
    void attackHero_blocked_shouldReturnMessage() {
        Hero hero = new Hero("x", "Iron Man", 90, 100, 100);
        when(heroRepository.findById("x")).thenReturn(Optional.of(hero));

        MessageResponseDTO msg = heroService.attackHero("x", new AttackRequestDTO(70));

        assertEquals("O herói não sofreu danos, tente novamente.", msg.getMessage());
        verify(heroRepository, never()).save(any(Hero.class));
    }

    @Test
    void attackHero_success_updatesHealth() {
        Hero hero = new Hero("y", "Cap", 60, 20, 100);
        when(heroRepository.findById("y")).thenReturn(Optional.of(hero));

        MessageResponseDTO msg = heroService.attackHero("y", new AttackRequestDTO(70));

        ArgumentCaptor<Hero> captor = ArgumentCaptor.forClass(Hero.class);
        verify(heroRepository, times(1)).save(captor.capture());
        Hero updated = captor.getValue();
        assertEquals(50, updated.getHealth());
        assertTrue(msg.getMessage().contains("Vida atual: 50"));
    }

    @Test
    void getHeroes_shouldMapList() {
        List<Hero> list = List.of(
                new Hero("1", "Thor", 80, 60, 100),
                new Hero("2", "Hulk", 70, 50, 90)
        );
        when(heroRepository.findAll(any(Specification.class))).thenReturn(list);

        List<HeroResponseDTO> res = heroService.getHeroes(null, null, null);

        assertEquals(2, res.size());
        assertEquals("Thor", res.get(0).getName());
    }
}