package br.com.berdnaski.heroes.domain.hero.specification;

import br.com.berdnaski.heroes.domain.hero.Hero;
import org.springframework.data.jpa.domain.Specification;

public class HeroSpecification {

    public static Specification<Hero> withName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Hero> withAttackPower(Integer attackPower) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("attackPower"), attackPower);
    }

    public static Specification<Hero> withDefensePower(Integer defensePower) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("defensePower"), defensePower);
    }
}