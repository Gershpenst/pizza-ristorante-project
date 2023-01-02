package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity
public abstract class BaseEntity {
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
}