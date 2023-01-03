package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
