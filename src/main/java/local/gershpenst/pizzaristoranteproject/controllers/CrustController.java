package local.gershpenst.pizzaristoranteproject.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import local.gershpenst.pizzaristoranteproject.model.Crust;
import local.gershpenst.pizzaristoranteproject.model.consumer.CrustConsumer;
import local.gershpenst.pizzaristoranteproject.repository.CrustRepository;
import local.gershpenst.pizzaristoranteproject.service.CrustService;

@RestController
@RequestMapping("/crust")
public class CrustController {

    private final CrustRepository crustRepository;

    public CrustController(CrustRepository crustRepository) {
        this.crustRepository = crustRepository;
    }

    @GetMapping({"", "/"})
    public List<Crust> getCrusts() {
        return CrustService.getAllCrust(crustRepository);
    }

    @GetMapping("/{id}")
    public Crust getCrust(@PathVariable("id") Long id) {
        return CrustService.getCrustService(crustRepository, id);
    }

    @PostMapping({"", "/"})
    public Crust addCrust(@Valid @RequestBody CrustConsumer crustConsumer) {
        return CrustService.addCrustService(crustRepository, crustConsumer);
    }

    @DeleteMapping("/{id}")
    public void deleteCrust(@PathVariable("id") Long id) {
        CrustService.deleteCrust(crustRepository, id);
    }

    @PutMapping("/{id}")
    public Crust putCrust(@PathVariable("id") Long id, @Valid @RequestBody CrustConsumer crustConsumer) {
        return CrustService.updateCrust(crustRepository, id, crustConsumer);
    }
}
