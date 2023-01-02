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

import jakarta.validation.Valid;
import local.gershpenst.pizzaristoranteproject.model.Dough;
import local.gershpenst.pizzaristoranteproject.model.consumer.DoughConsumer;
import local.gershpenst.pizzaristoranteproject.repository.DoughRepository;
import local.gershpenst.pizzaristoranteproject.service.DoughService;

@RestController
@RequestMapping("/dough")
public class DoughController {

    private final DoughRepository doughRepository;

    public DoughController(DoughRepository doughRepository) {
        this.doughRepository = doughRepository;
    }
    
    @GetMapping({"", "/"})
    public List<Dough> getAllDough() {
        return DoughService.getAllDoughService(doughRepository);
    }

    @GetMapping("/{id}")
    public Dough getSpecificDough(@PathVariable("id") Long id) {
        return DoughService.getDoughByIdService(doughRepository, id);
    }

    @PostMapping({"", "/"})
    public Dough addDough(@Valid @RequestBody DoughConsumer dough) {
        return DoughService.addDough(doughRepository, dough);
    }

    @DeleteMapping("/{id}")
    public void deleteDough(@PathVariable("id") Long id) {
        DoughService.deleteDough(doughRepository, id);
    }

    @PutMapping("/{id}")
    public Dough updateDough(@PathVariable("id") Long id, @Valid @RequestBody DoughConsumer dough) {
        return DoughService.updateDough(doughRepository, id, dough);
    }
}
