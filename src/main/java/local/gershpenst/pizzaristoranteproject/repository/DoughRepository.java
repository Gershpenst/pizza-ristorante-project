package local.gershpenst.pizzaristoranteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import local.gershpenst.pizzaristoranteproject.model.Dough;

import java.util.Optional;

@Repository
public interface DoughRepository extends JpaRepository<Dough, Long> {
    Optional<Dough> findByName(String name);
}
