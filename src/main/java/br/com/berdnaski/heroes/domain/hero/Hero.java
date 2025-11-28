package br.com.berdnaski.heroes.domain.hero;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "heroes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "attack_power", nullable = false)
    private Integer attackPower;

    @Column(name = "defense_power", nullable = false)
    private Integer defensePower;

    @Column(nullable = false)
    private Integer health;
}