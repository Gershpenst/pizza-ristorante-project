package local.gershpenst.pizzaristoranteproject.model.consumer;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CrustConsumer(@NotNull(message = "name must not be null.")
                            @NotEmpty(message = "name must not be empty.")
                            @Size(min = 2, max = 32, message = "name must be between 2 and 32 characters long.")
                            String name, 

                            @NotNull(message = "price must not be null.")
                            @Digits(integer = 3, fraction = 2, message = "Fractions must be 2 digits.")
                            @DecimalMin(value = "0.0", inclusive = true, message = "price cannot be less than 0.0€.")
                            Double price) {}
