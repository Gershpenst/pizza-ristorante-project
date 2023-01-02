package local.gershpenst.pizzaristoranteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import local.gershpenst.pizzaristoranteproject.model.Toppings;

import java.util.Optional;

public interface ToppingsRepository extends JpaRepository<Toppings, Long> {
    Optional<Toppings> findByName(String name);
}
