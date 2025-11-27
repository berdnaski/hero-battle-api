package br.com.berdnaski.heroes.domain.hero;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "heroes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Poder de ataque não pode ser nulo")
    @Min(value = 1, message = "Poder de ataque deve ser maior do que zero")
    @Column(name = "attack_power", nullable = false)
    private Integer attackPower;

    @NotNull(message = "Poder de defesa não pode ser nulo")
    @Min(value = 1, message = "Poder de defesa deve ser maior do que zero")
    @Column(name = "defense_power", nullable = false)
    private Integer defensePower;

    @NotNull(message = "Vida não pode ser nulo")
    @Min(value = 1, message = "Vida deve ser maior do que zero")
    @Column(nullable = false)
    private Integer health;
}
