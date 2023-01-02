package local.gershpenst.pizzaristoranteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import local.gershpenst.pizzaristoranteproject.model.Sauce;

import java.util.Optional;

@Repository
public interface SauceRepository extends JpaRepository<Sauce, Long> {
    Optional<Sauce> findByName(String name);
}
