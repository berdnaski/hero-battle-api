package br.com.berdnaski.heroes.repositories;

import br.com.berdnaski.heroes.domain.hero.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID>, JpaSpecificationExecutor<Hero> {
    Optional<Hero> findByName(String name);
}