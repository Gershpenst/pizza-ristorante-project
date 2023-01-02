package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Crust")
public class Crust {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",
            nullable = false,
            unique = true)
    private Long id;

    // @NotNull(message = "crust_name must not be null.")
    // @NotEmpty(message = "crust_name must not be empty.")
    // @Size(min = 2, max = 32, message = "crust_name must be between 2 and 32 characters long.")
    @Column(name = "name",
            nullable = false,
            unique = true)
    private String name;

    // @NotNull(message = "price must not be null.")
    // @Digits(integer = 3, fraction = 2, message = "Fractions must be 2 digits.")
    // @DecimalMin(value = "0.0", inclusive = true, message = "price cannot be less than 0.0€.")
    @Column(name = "price",
            nullable = false)
    private Double price; 

    public Crust() {}

    public Crust(String name, Double price) {
        this.name = name;
        this.price = price;
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
        Crust other = (Crust) obj;
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
        return "Crust [id=" + id + ", crust_name=" + name + ", price=" + price + "]";
    }
}