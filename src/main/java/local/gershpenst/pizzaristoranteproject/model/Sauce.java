package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import local.gershpenst.pizzaristoranteproject.model.consumer.SauceConsumer;

@Entity(name = "Sauce")
@Table(name = "Sauce")
public class Sauce extends BaseEntity {
    public Sauce() {}

    public Sauce(String name, Double price) {
        super(name, price);
    }

    public Sauce(SauceConsumer sauceConsumer) {
        super(sauceConsumer);
    }
}
