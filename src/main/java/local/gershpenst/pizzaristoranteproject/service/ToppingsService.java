package local.gershpenst.pizzaristoranteproject.service;

import java.util.List;
import java.util.Optional;

import local.gershpenst.pizzaristoranteproject.exceptions.ConflictException;
import local.gershpenst.pizzaristoranteproject.exceptions.NotFoundException;
import local.gershpenst.pizzaristoranteproject.utils.ArgumentVerification;
import org.springframework.stereotype.Service;

import local.gershpenst.pizzaristoranteproject.model.Toppings;
import local.gershpenst.pizzaristoranteproject.model.consumer.ToppingsConsumer;
import local.gershpenst.pizzaristoranteproject.repository.ToppingsRepository;

@Service
public class ToppingsService {

    private ToppingsService() {}
    
    public static List<Toppings> getAllToppingsService(ToppingsRepository toppingsRepository) {
        return toppingsRepository.findAll();
    }

    public static Toppings getToppings(ToppingsRepository toppingsRepository, Long id) {
        return toppingsRepository.findById(id).orElseThrow(() -> new NotFoundException("The id " + id + " doesn't exists for toppings."));
    }

    public static Toppings addToppings(ToppingsRepository toppingsRepository, ToppingsConsumer toppingConsumer) {
        String name = toppingConsumer.name();

        Optional<Toppings> topping = getToppingsByName(toppingsRepository, name);
        System.out.println("topping : " + topping);

        topping.ifPresent(s -> {
            throw new ConflictException("The name " + name + " exists for toppings.");
        });

        return toppingsRepository.save(new Toppings(
                name,
                toppingConsumer.price()
        ));
    }

    public static void deleteToppings(ToppingsRepository toppingsRepository, Long id) {
        toppingsRepository.deleteById(id);
    }

    public static Toppings updateToppings(ToppingsRepository toppingsRepository, Long id, ToppingsConsumer toppingConsumer) {
        Toppings topping = getToppings(toppingsRepository, id);

        ArgumentVerification.updateIfPresent(topping::setName, toppingConsumer.name());
        ArgumentVerification.updateIfPresent(topping::setPrice, toppingConsumer.price());

        return toppingsRepository.save(topping);
    }

    public static List<Toppings> getToppingsByIdsService(ToppingsRepository toppingsRepository, List<Long> ids) {
        return toppingsRepository.findAllById(ids);
    }

    public static Optional<Toppings> getToppingsByName(ToppingsRepository toppingsRepository, String name) {
        return toppingsRepository.findByName(name);
    }
}
