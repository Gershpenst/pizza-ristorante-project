package local.gershpenst.pizzaristoranteproject.model.consumer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DoughConsumer(@NotNull(message = "name cannot be null.")
                            @Size(message = "name must be between 2 and 32 characters.", min = 2, max = 32)
                            String name, 
                            
                            @NotNull(message = "price cannot be null.")
                            Double price) {}
