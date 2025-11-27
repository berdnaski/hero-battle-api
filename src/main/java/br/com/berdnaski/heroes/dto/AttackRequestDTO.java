package br.com.berdnaski.heroes.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttackRequestDTO {
    @NotNull(message = "Ataque não pode ser nulo ou vazio")
    @Min(value = 1, message = "Ataque deve ser maior do que zero")
    private Integer attackValue;
}