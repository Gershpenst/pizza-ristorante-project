package local.gershpenst.pizzaristoranteproject.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import local.gershpenst.pizzaristoranteproject.model.consumer.BaseConsumer;

import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",
            nullable = false,
            unique = true,
            updatable = false)
    protected Long id;

    @Column(name = "name",
            nullable = false,
            unique = true,
            length = 32)
    protected String name;

    @Column(name = "price",
            nullable = false,
            scale=2)
    private Double price;

    protected BaseEntity() {}

    protected BaseEntity(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    protected BaseEntity(BaseConsumer consumer) {
        this.name = consumer.name();
        this.price = consumer.price();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}