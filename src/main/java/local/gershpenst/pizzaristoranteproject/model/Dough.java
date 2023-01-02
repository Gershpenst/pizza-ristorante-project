package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "Dough")
public class Dough {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dough_id")
    private Long id;

    @NotNull(message = "name cannot be null.")
    @Size(message = "name must be between 2 and 32 characters.", min = 2, max = 32)
    @Column(name = "name",
            nullable = false,
            unique = true)
    private String name;

    @NotNull(message = "price cannot be null.")
    @Column(name = "price",
            nullable = false)
    private Double price;

    public Dough() {}

    public Dough(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    // public Dough(String name, Double price, Crust crust) {
    //     this.name = name;
    //     this.price = price;
    //     this.crust = crust;
    // }

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

    // public Crust getCrust() {
    //     return crust;
    // }

    // public void setCrust(Crust crust) {
    //     this.crust = crust;
    // }

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
        Dough other = (Dough) obj;
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
        return "Dough [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}
