package br.com.berdnaski.heroes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroResponseDTO {
    private String id;
    private String name;
    private Integer attackPower;
    private Integer defensePower;
    private Integer health;
}