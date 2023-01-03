package local.gershpenst.pizzaristoranteproject.service;

import java.util.List;
import java.util.Optional;

import local.gershpenst.pizzaristoranteproject.exceptions.ApiRequestException;
import local.gershpenst.pizzaristoranteproject.exceptions.ConflictException;
import local.gershpenst.pizzaristoranteproject.exceptions.NotFoundException;
import local.gershpenst.pizzaristoranteproject.utils.ArgumentVerification;
import org.springframework.stereotype.Service;

import local.gershpenst.pizzaristoranteproject.model.Dough;
import local.gershpenst.pizzaristoranteproject.model.consumer.DoughConsumer;
import local.gershpenst.pizzaristoranteproject.repository.DoughRepository;

@Service
public class DoughService {

    private DoughService() {}
    
    public static List<Dough> getAllDoughService(DoughRepository doughRepository) {
        return doughRepository.findAll();
    }

    public static Dough getDoughByIdService(DoughRepository doughRepository, Long id) {
        return doughRepository.findById(id).orElseThrow(() -> new NotFoundException("The id " + id + " doesn't exists for dough."));
    }

    public static Dough addDough(DoughRepository doughRepository, DoughConsumer doughConsumer) {
        String name = doughConsumer.name();
        getFindDoughByName(doughRepository, name).ifPresent(
                msg -> {
                    throw new ConflictException("The name " + name + " exists for dough.");
                }
        );

        return doughRepository.save(new Dough(
            name,
            doughConsumer.price()
        ));
    }

    public static void deleteDough(DoughRepository doughRepository, Long id) {
        doughRepository.deleteById(id);
    }

    public static Dough updateDough(DoughRepository doughRepository, Long id, DoughConsumer doughConsumer) {
        Dough dough = getDoughByIdService(doughRepository, id);

        ArgumentVerification.updateIfPresent(dough::setName, doughConsumer.name());
        ArgumentVerification.updateIfPresent(dough::setPrice, doughConsumer.price());

        return doughRepository.save(dough);
    }

    public static Optional<Dough> getFindDoughByName(DoughRepository doughRepository, String name) {
        return doughRepository.findByName(name);
    }
}
