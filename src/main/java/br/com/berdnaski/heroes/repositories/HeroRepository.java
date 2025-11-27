package br.com.berdnaski.heroes.repositories;

import br.com.berdnaski.heroes.domain.hero.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String>, JpaSpecificationExecutor<Hero> {
    Optional<Hero> findByName(String name);
}