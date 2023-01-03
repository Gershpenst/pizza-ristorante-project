package local.gershpenst.pizzaristoranteproject.model.consumer;

import java.util.List;

public record PizzaConsumer(String name,
                            Long doughId,
                            Long crustId,
                            Long sauceId,
                            List<Long> toppingsId) {}
