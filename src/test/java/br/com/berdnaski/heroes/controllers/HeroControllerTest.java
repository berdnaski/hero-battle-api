package br.com.berdnaski.heroes.controllers;


import br.com.berdnaski.heroes.dto.AttackRequestDTO;
import br.com.berdnaski.heroes.dto.HeroRequestDTO;
import br.com.berdnaski.heroes.dto.HeroResponseDTO;
import br.com.berdnaski.heroes.dto.MessageResponseDTO;
import br.com.berdnaski.heroes.services.HeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HeroController.class)
class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private HeroService heroService;

    @Test
    void getHeroes_shouldReturnOk() throws Exception {
        List<HeroResponseDTO> list = List.of(
                new HeroResponseDTO("1", "Thor", 80, 60, 100)
        );
        when(heroService.getHeroes(null, null, null)).thenReturn(list);

        mockMvc.perform(get("/api/heroes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Thor"));
    }

    @Test
    void create_shouldReturnOk() throws Exception {
        HeroRequestDTO req = new HeroRequestDTO("Hulk", 70, 50, 100);
        HeroResponseDTO res = new HeroResponseDTO("x", "Hulk", 70, 50, 100);
        when(heroService.create(req)).thenReturn(res);

        mockMvc.perform(post("/api/heroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("x"))
                .andExpect(jsonPath("$.name").value("Hulk"));
    }

    @Test
    void attack_shouldReturnOk() throws Exception {
        when(heroService.attackHero("1", new AttackRequestDTO(70)))
                .thenReturn(new MessageResponseDTO("O herói sofreu danos. Vida atual: 90"));

        mockMvc.perform(put("/api/heroes/1/attack")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AttackRequestDTO(70))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }
}