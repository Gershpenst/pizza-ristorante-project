package local.gershpenst.pizzaristoranteproject.service;

import java.util.List;
import java.util.Optional;

import local.gershpenst.pizzaristoranteproject.utils.ArgumentVerification;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import local.gershpenst.pizzaristoranteproject.exceptions.ApiRequestException;
import local.gershpenst.pizzaristoranteproject.model.Crust;
import local.gershpenst.pizzaristoranteproject.model.consumer.CrustConsumer;
import local.gershpenst.pizzaristoranteproject.repository.CrustRepository;

@Service
public class CrustService {
    
    public static List<Crust> getAllCrust(CrustRepository crustRepository) {
        return crustRepository.findAll();
    }

    public static Crust getCrustService(CrustRepository crustRepository, Long id) {
        return crustRepository.findById(id).orElseThrow(() -> new ApiRequestException("The id " + id + " doesn't exists for crust."));
    }

    public static Crust addCrustService(CrustRepository crustRepository, @Valid CrustConsumer crustConsumer) {
        String name = crustConsumer.name();
        getCrustByName(crustRepository, name).ifPresent(
                msg -> {
                    throw new ApiRequestException("The name " + name + " exists for crust.");
                }
        );

        Crust crust = new Crust(
                name,
                crustConsumer.price()
        );
        System.out.println("[addCrustService] crust : " + crust);
        return crustRepository.save(crust);
    }

    public static void deleteCrust(CrustRepository crustRepository, Long id) {
        crustRepository.deleteById(id);
    }

    public static Crust updateCrust(CrustRepository crustRepository, Long id, CrustConsumer crustConsumer) {
        Crust crust = getCrustService(crustRepository, id);

        ArgumentVerification.updateIfPresent(crust::setName, crustConsumer.name());
        ArgumentVerification.updateIfPresent(crust::setPrice, crustConsumer.price());

        return crustRepository.save(crust);
    }

    public static Optional<Crust> getCrustByName(CrustRepository crustRepository, String name) {
        return crustRepository.findByName(name);
    }
}
