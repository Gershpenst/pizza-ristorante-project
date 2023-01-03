package local.gershpenst.pizzaristoranteproject.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;


@Entity(name = "Pizza")
@Table(name = "Pizza")
public class Pizza extends BaseEntity {
    @PrimaryKeyJoinColumn(name = "dough")
    @OneToOne
    @JoinTable( 
        name = "pizza_dough",
        joinColumns = @JoinColumn(table = "Pizza", name = "id"),
        inverseJoinColumns = @JoinColumn(table = "Dough", name = "id")
    )
    private Dough dough;
    
    @PrimaryKeyJoinColumn(name = "sauce")
    @OneToOne
    @JoinTable( name = "pizza_sauce",
                joinColumns = @JoinColumn(table = "Pizza", name = "id"),
                inverseJoinColumns = @JoinColumn(table = "Sauce", name = "id"))
    private Sauce sauce;

    @Column(name = "toppings",
            nullable = false)
    @ManyToMany
    @JoinTable(
        name = "pizza_toppings",
        joinColumns = @JoinColumn(table = "Pizza", name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(table = "Topping", name = "id")
    )
    private final Set<Toppings> toppings = new HashSet<>();

    @OneToOne
    @JoinTable(name = "pizza_crust",
                joinColumns = @JoinColumn(table = "Pizza", name = "id"),
                inverseJoinColumns = @JoinColumn(table = "Crust", name = "id"))
    private Crust crust;

    public Pizza() {}

    public Pizza setPizzaId(Long id) {
        this.id = id;
        return this;
    }

    public Pizza setPizzaName(String name) {
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

    public Pizza setPizzaPrice(Double price) {
        this.setPrice(price);
        return this;
    }

    public Pizza build() {
        this.setPrice(processTotalForOnePizza());
        return this;
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

    public Double processTotalForOnePizza() {
        return  crust.getPrice() +
                dough.getPrice() +
                sauce.getPrice() +
                toppings.stream().map(Toppings::getPrice).reduce(0.0, Double::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(name, pizza.name) && Objects.equals(dough, pizza.dough) && Objects.equals(sauce, pizza.sauce) && Objects.equals(toppings, pizza.toppings) && Objects.equals(crust, pizza.crust);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, dough, sauce, toppings, crust);
    }
}