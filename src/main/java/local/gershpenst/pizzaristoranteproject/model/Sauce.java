package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import local.gershpenst.pizzaristoranteproject.model.consumer.SauceConsumer;

@Entity(name = "Sauce")
public class Sauce {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sauce_id",
            nullable = false,
            unique = true)
    private Long id;

    @NotNull(message = "name cannot be null.")
    @Size(min = 3, max = 32, message = "name must be between 3 and 32 characters.")
    @Column(name = "name",
            nullable = false,
            unique = true)
    private String name;

    @NotNull(message = "price cannot be null.")
    @Column(name = "price",
            nullable = false)
    private Double price;

    public Sauce() {}

    public Sauce(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Sauce(SauceConsumer sauceConsumer) {
        this.name = sauceConsumer.name();
        this.price = sauceConsumer.price();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sauce other = (Sauce) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Sauce [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}
