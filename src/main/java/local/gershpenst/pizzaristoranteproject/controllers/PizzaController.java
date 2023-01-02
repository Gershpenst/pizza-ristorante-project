package local.gershpenst.pizzaristoranteproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.gershpenst.pizzaristoranteproject.model.Pizza;
import local.gershpenst.pizzaristoranteproject.model.consumer.PizzaConsumer;
import local.gershpenst.pizzaristoranteproject.repository.CrustRepository;
import local.gershpenst.pizzaristoranteproject.repository.DoughRepository;
import local.gershpenst.pizzaristoranteproject.repository.PizzaRepository;
import local.gershpenst.pizzaristoranteproject.repository.SauceRepository;
import local.gershpenst.pizzaristoranteproject.repository.ToppingsRepository;
import local.gershpenst.pizzaristoranteproject.service.PizzaService;

@RestController
@RequestMapping("/pizza")
public class PizzaController {
    
    private final PizzaRepository pizzaRepository;
    private final ToppingsRepository toppingsRepository;
    private final DoughRepository doughRepository;
    private final CrustRepository crustRepository;
    private final SauceRepository sauceRepository;

    public PizzaController(PizzaRepository pizzaRepository, ToppingsRepository toppingsRepository, DoughRepository doughRepository, CrustRepository crustRepository, SauceRepository sauceRepository) {
        this.pizzaRepository = pizzaRepository;
        this.toppingsRepository = toppingsRepository;
        this.doughRepository = doughRepository;
        this.crustRepository = crustRepository;
        this.sauceRepository = sauceRepository;
    }

    @GetMapping({"", "/"})
    public List<Pizza> getPizzas() {
        return PizzaService.getAllPizzas(pizzaRepository);
    }

    @GetMapping("/{id}")
    public Pizza getSpecificPizza(@PathVariable("id") Long id) {
        return PizzaService.getPizza(pizzaRepository, id);
    }

    @PostMapping({"", "/"})
    public Pizza postPizza(@RequestBody PizzaConsumer pizza_consumer) {
        return PizzaService.addPizza(pizzaRepository, doughRepository, crustRepository, sauceRepository, toppingsRepository, pizza_consumer);
    }

    @PutMapping("/{id}")
    public Pizza updatePizza(@PathVariable("id") Long id, @RequestBody PizzaConsumer pizza_consumer) {
        return PizzaService.updatePizza(id, pizza_consumer, pizzaRepository, doughRepository, crustRepository, sauceRepository, toppingsRepository);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable("id") Long id) {
        PizzaService.deletePizza(pizzaRepository, id);
    }
}