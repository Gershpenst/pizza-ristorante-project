package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.*;

@Entity(name = "Crust")
@Table(name = "Crust")
public class Crust extends BaseEntity {
    public Crust() {}

    public Crust(String name, Double price) {
        super(name, price);
    }
}