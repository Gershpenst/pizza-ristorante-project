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

import local.gershpenst.pizzaristoranteproject.model.Sauce;
import local.gershpenst.pizzaristoranteproject.model.consumer.SauceConsumer;
import local.gershpenst.pizzaristoranteproject.repository.SauceRepository;
import local.gershpenst.pizzaristoranteproject.service.SauceService;

@RestController
@RequestMapping("/sauce")
public class SauceController {

    private final SauceRepository sauceRepository;

    public SauceController(SauceRepository sauceRepository) {
        this.sauceRepository = sauceRepository;
    }

    @GetMapping({"", "/"})
    public List<Sauce> getSauces() {
        return SauceService.getSauceService(sauceRepository);
    }

    @GetMapping("/{id}")
    public Sauce getSauce(@PathVariable("id") Long id) {
        return SauceService.getSauceService(sauceRepository, id);
    }

    @PostMapping({"", "/"})
    public Sauce addSauce(@RequestBody SauceConsumer sauce_consumer) {
        return SauceService.addSauceService(sauceRepository, sauce_consumer);
    }

    @DeleteMapping("/{id}")
    public String deleteSauce(@PathVariable(name = "id") Long id) {
        System.out.println("ID : " + id);
        return SauceService.deleteSauce(sauceRepository, id);
    }

    @PutMapping("/{id}")
    public Sauce updateSauce(@PathVariable("id") Long id, @RequestBody SauceConsumer sauce_consumer) {
        return SauceService.updateSauceService(sauceRepository, id, sauce_consumer);
    }
}
