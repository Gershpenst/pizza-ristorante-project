package local.gershpenst.pizzaristoranteproject.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import org.springframework.beans.factory.annotation.Value;


/* Builder factory -- design pattern */
@Entity(name = "Pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",
            nullable = false,
            unique = true)
    private Long id;

    @Column(name = "name",
            nullable = false,
            unique = true)
    private String name;

    @PrimaryKeyJoinColumn(name = "dough")
    @OneToOne
    @JoinTable( 
        name = "pizza_dough",
        joinColumns = @JoinColumn(name = "id"),
        inverseJoinColumns = @JoinColumn(table = "Dough", name = "id")
    )
    private Dough dough;
    
    @PrimaryKeyJoinColumn(name = "sauce")
    @OneToOne
    @JoinTable( name = "pizza_sauce",
                joinColumns = @JoinColumn(name = "pizza_id"),
                inverseJoinColumns = @JoinColumn(table = "Sauce", name = "id"))
    private Sauce sauce;

    @Column(name = "toppings",
            nullable = false)
    @ManyToMany
    @JoinTable(
        name = "pizza_toppings",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name = "topping_id")
    )
    private final Set<Toppings> toppings = new HashSet<>();

    @OneToOne
    @JoinTable(name = "pizza_crust",
                joinColumns = @JoinColumn(name = "pizza_id"),
                inverseJoinColumns = @JoinColumn(name = "crust_id"))
    private Crust crust;

    @Column(name = "price",
            nullable = false)
    private Double price;

    public Pizza() {}

    public Pizza setId(Long id) {
        this.id = id;
        return this;
    }

    public Pizza setName(String name) {
        this.name = name;
        return this;
    }

    public Pizza setCrust(Crust crust) {
        this.crust = crust;
        return this;
    }

    public Pizza setDough(Dough dough) {
        this.dough = dough;
        return this;
    }

    public Pizza setSauce(Sauce sauce) {
        this.sauce = sauce;
        return this;
    }

    public Pizza addToppings(Toppings topping) {
        this.toppings.add(topping);
        return this;
    }

    public Pizza addToppingsAll(List<Toppings> toppings) {
        this.toppings.addAll(toppings);
        return this;
    }

    public Pizza deleteToppings(Toppings topping) {
        this.toppings.remove(topping);
        return this;
    }

    public Pizza setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Pizza build() {
        this.price = getTotal();
        return this;
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Dough getDough() {
        return dough;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Set<Toppings> getToppings() {
        return toppings;
    }

    public Crust getCrust() {
        return crust;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotal() {
        return  crust.getPrice() +
                dough.getPrice() +
                sauce.getPrice() +
                toppings.stream().map(Toppings::getPrice).reduce(0.0, Double::sum);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((dough == null) ? 0 : dough.hashCode());
        result = prime * result + ((sauce == null) ? 0 : sauce.hashCode());
        result = prime * result + ((toppings == null) ? 0 : toppings.hashCode());
        result = prime * result + ((crust == null) ? 0 : crust.hashCode());
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
        Pizza other = (Pizza) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (dough == null) {
            if (other.dough != null)
                return false;
        } else if (!dough.equals(other.dough))
            return false;
        if (sauce == null) {
            if (other.sauce != null)
                return false;
        } else if (!sauce.equals(other.sauce))
            return false;
        if (toppings == null) {
            if (other.toppings != null)
                return false;
        } else if (!toppings.equals(other.toppings))
            return false;
        if (crust == null) {
            if (other.crust != null)
                return false;
        } else if (!crust.equals(other.crust))
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
        return "Pizza [id=" + id + ", name=" + name + ", dough=" + dough + ", sauce=" + sauce + ", toppings=" + toppings
                + ", crust=" + crust + ", price=" + price + "]";
    }
}