package local.gershpenst.pizzaristoranteproject.service;

import java.util.List;
import java.util.Optional;

import local.gershpenst.pizzaristoranteproject.exceptions.ApiRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import local.gershpenst.pizzaristoranteproject.model.Crust;
import local.gershpenst.pizzaristoranteproject.model.Dough;
import local.gershpenst.pizzaristoranteproject.model.Pizza;
import local.gershpenst.pizzaristoranteproject.model.Sauce;
import local.gershpenst.pizzaristoranteproject.model.Toppings;
import local.gershpenst.pizzaristoranteproject.model.consumer.PizzaConsumer;
import local.gershpenst.pizzaristoranteproject.repository.CrustRepository;
import local.gershpenst.pizzaristoranteproject.repository.DoughRepository;
import local.gershpenst.pizzaristoranteproject.repository.PizzaRepository;
import local.gershpenst.pizzaristoranteproject.repository.SauceRepository;
import local.gershpenst.pizzaristoranteproject.repository.ToppingsRepository;

@Service
public class PizzaService {

    private PizzaService() {}
    
    public static List<Pizza> getAllPizzas(PizzaRepository pizzaRepository) {
        return pizzaRepository.findAll();
    }

    public static Pizza getPizza(PizzaRepository pizzaRepository, Long id) {
        return pizzaRepository.findById(id).orElseThrow(() -> new ApiRequestException("The id " + id + " doesn't exists for Pizza."));
    }

    private static Pizza createPizzaObject(DoughRepository doughRepository,
                                    CrustRepository crustRepository,
                                    SauceRepository sauceRepository,
                                    ToppingsRepository toppingRepository,
                                    PizzaConsumer pizzaConsumer) {

        String name = pizzaConsumer.name();
        List<Toppings> toppings = ToppingsService.getToppingsByIdsService(toppingRepository, pizzaConsumer.toppings_id());
        Crust crust = CrustService.getCrustService(crustRepository, pizzaConsumer.crust_id());
        Dough dough = DoughService.getDoughByIdService(doughRepository, pizzaConsumer.dough_id());
        Sauce sauce = SauceService.getSauceService(sauceRepository, pizzaConsumer.sauce_id());

        System.out.println("name : " + name + "\ntoppings : " + toppings + "\ncrust : " + crust + "\ndough : " + dough + "\nsauce : " + sauce);

        Pizza pizza = new Pizza()
                .setName(name)
                .setCrust(crust)
                .setSauce(sauce)
                .setDough(dough)
                .addToppingsAll(toppings)
                .build();

        System.out.println("Pizza : " + pizza);
        return pizza;
    }

    public static Pizza addPizza(PizzaRepository pizzaRepository,
                                DoughRepository doughRepository,
                                CrustRepository crustRepository,
                                SauceRepository sauceRepository,
                                ToppingsRepository toppingRepository,
                                PizzaConsumer pizzaConsumer) {

        String name = pizzaConsumer.name();
        findPizzaById(pizzaRepository, name).ifPresent(
                msg -> {
                    throw new ApiRequestException("The name " + name + " exists for pizza.");
                }
        );
        
        return pizzaRepository.save(
            createPizzaObject(
                doughRepository,
                crustRepository,
                sauceRepository,
                toppingRepository,
                pizzaConsumer)
            );
    }

    public static Pizza updatePizza(Long id,
                                    PizzaConsumer pizzaConsumer,
                                    PizzaRepository pizzaRepository,
                                    DoughRepository doughRepository,
                                    CrustRepository crustRepository,
                                    SauceRepository sauceRepository,
                                    ToppingsRepository toppingRepository) {

        // Find if there is an existing Pizza
        getPizza(pizzaRepository, id);

        Pizza pizza = createPizzaObject(
            doughRepository,
            crustRepository,
            sauceRepository,
            toppingRepository,
            pizzaConsumer)
        .setId(id);

        return pizzaRepository.save(pizza);
    }

    public static void deletePizza(PizzaRepository pizzaRepository, Long id) {
        pizzaRepository.deleteById(id);
    }

    public static Optional<Pizza> findPizzaById(PizzaRepository pizzaRepository, String name) {
        return pizzaRepository.findByName(name);
    }
}
