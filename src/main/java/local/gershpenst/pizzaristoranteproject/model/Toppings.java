package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.*;

@Entity(name = "Topping")
@Table(name = "Topping")
public class Toppings extends BaseEntity {
    public Toppings() {}

    public Toppings(String name, Double price) {
        super(name, price);
    }
}