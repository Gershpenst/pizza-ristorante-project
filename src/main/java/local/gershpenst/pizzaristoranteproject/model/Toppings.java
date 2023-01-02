package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Toppings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topping_id")
    private Long id;

    @Column(name = "name",
            nullable = false,
            unique = true)
    private String name;

    @Column(name = "price",
            nullable = false)
    private Double price;

    public Toppings() {}

    public Toppings(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Toppings [id=" + id + ", name=" + name + ", price=" + price + "]";
    }

}