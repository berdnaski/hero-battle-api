package br.com.berdnaski.heroes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroRequestDTO {
    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    private String name;

    @NotNull(message = "Poder de ataque não pode ser nulo ou vazio")
    @Min(value = 1, message = "Poder de ataque deve ser maior do que zero")
    private Integer attackPower;

    @NotNull(message = "Poder de defesa não pode ser nulo ou vazio")
    @Min(value = 1, message = "Poder de defesa deve ser maior do que zero")
    private Integer defensePower;

    @NotNull(message = "Vida não pode ser nula ou vazia")
    @Min(value = 1, message = "Vida de ataque deve ser maior do que zero")
    private Integer health;
}