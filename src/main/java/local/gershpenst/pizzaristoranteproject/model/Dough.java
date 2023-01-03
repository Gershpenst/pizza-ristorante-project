package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.*;

@Entity(name = "Dough")
@Table(name = "Dough")
public class Dough extends BaseEntity {
    public Dough() {}

    public Dough(String name, Double price) {
        super(name, price);
    }
}
