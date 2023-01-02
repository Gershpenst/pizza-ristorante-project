package local.gershpenst.pizzaristoranteproject.model.consumer;

import java.util.List;

public record PizzaConsumer(String name,
                            Long dough_id, 
                            Long crust_id, 
                            Long sauce_id, 
                            List<Long> toppings_id) {}
