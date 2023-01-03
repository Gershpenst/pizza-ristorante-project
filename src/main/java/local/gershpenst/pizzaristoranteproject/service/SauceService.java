package local.gershpenst.pizzaristoranteproject.service;

import java.util.List;
import java.util.Optional;

import local.gershpenst.pizzaristoranteproject.exceptions.ConflictException;
import local.gershpenst.pizzaristoranteproject.exceptions.NotFoundException;
import local.gershpenst.pizzaristoranteproject.utils.ArgumentVerification;
import org.springframework.stereotype.Service;

import local.gershpenst.pizzaristoranteproject.model.Sauce;
import local.gershpenst.pizzaristoranteproject.model.consumer.SauceConsumer;
import local.gershpenst.pizzaristoranteproject.repository.SauceRepository;

@Service
public class SauceService {

    private SauceService() {}
    
    public static List<Sauce> getSauceService(SauceRepository sauceRepository) {
        return sauceRepository.findAll();
    }

    public static Sauce getSauceService(SauceRepository sauceRepository, Long id) {
        return sauceRepository.findById(id).orElseThrow(() -> new NotFoundException("The id " + id + " doesn't exists for sauce."));
    }

    public static Sauce addSauceService(SauceRepository sauceRepository, SauceConsumer sauceConsumer) {
        String name = sauceConsumer.name();
        getSauceByName(sauceRepository, name).ifPresent(
                msg -> {
                    throw new ConflictException("The name " + name + " exists for sauce.");
                }
        );

        return sauceRepository.save(new Sauce(sauceConsumer));
    }

    public static void deleteSauce(SauceRepository sauceRepository, Sauce sauce) {
        sauceRepository.delete(sauce);
    }

    public static String deleteSauce(SauceRepository sauceRepository, Long sauceId) {
        sauceRepository.deleteById(sauceId);
        return sauceId + " has been deleted.";
    }

    public static Sauce updateSauceService(SauceRepository sauceRepository, Long id, SauceConsumer sauceConsumer) {
        Sauce sauce = getSauceService(sauceRepository, id);

        ArgumentVerification.updateIfPresent(sauce::setName, sauceConsumer.name());
        ArgumentVerification.updateIfPresent(sauce::setPrice, sauceConsumer.price());

        return sauceRepository.save(sauce);
    }

    public static Optional<Sauce> getSauceByName(SauceRepository sauceRepository, String name) {
        return sauceRepository.findByName(name);
    }
}
