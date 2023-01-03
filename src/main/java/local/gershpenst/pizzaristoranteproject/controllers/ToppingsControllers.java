package local.gershpenst.pizzaristoranteproject.controllers;

import java.util.Arrays;
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

import jakarta.websocket.server.PathParam;
import local.gershpenst.pizzaristoranteproject.model.Toppings;
import local.gershpenst.pizzaristoranteproject.model.consumer.ToppingsConsumer;
import local.gershpenst.pizzaristoranteproject.repository.ToppingsRepository;
import local.gershpenst.pizzaristoranteproject.service.ToppingsService;

@RestController
@RequestMapping("/topping")
public class ToppingsControllers {

    private final ToppingsRepository toppingsRepository;

    public ToppingsControllers(ToppingsRepository toppingsRepository) {
        this.toppingsRepository = toppingsRepository;
    }

    @GetMapping({"", "/"})
    public List<Toppings> getToppings() {
        return ToppingsService.getAllToppingsService(toppingsRepository);
    }

    @GetMapping({"/ids", "/ids/"})
    public List<Toppings> getToppingsByIds(@PathParam(value = "ids[]") Long[] ids) {
        System.out.println("IDS : " + ids.length);
        return ToppingsService.getToppingsByIdsService(toppingsRepository, Arrays.asList(ids));
    }

    @GetMapping("/{id}")
    public Toppings getTopping(@PathVariable("id") Long id) {
        return ToppingsService.getToppings(toppingsRepository, id);
    }

    @PostMapping({"", "/"})
    public Toppings addTopping(@RequestBody ToppingsConsumer toppingConsumer) {
        return ToppingsService.addToppings(toppingsRepository, toppingConsumer);
    }

    @PutMapping("/{id}")
    public Toppings updateToppings(@PathVariable("id") Long id, @RequestBody ToppingsConsumer toppingConsumer) {
        return ToppingsService.updateToppings(toppingsRepository, id, toppingConsumer);
    }

    @DeleteMapping("/{id}")
    public void deleteToppings(@PathVariable Long id) {
        ToppingsService.deleteToppings(toppingsRepository, id);
    }
}
