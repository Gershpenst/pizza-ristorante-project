package local.gershpenst.pizzaristoranteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import local.gershpenst.pizzaristoranteproject.model.Crust;

import java.util.Optional;

@Repository
public interface CrustRepository extends JpaRepository<Crust, Long> {
    Optional<Crust> findByName(String name);
}
