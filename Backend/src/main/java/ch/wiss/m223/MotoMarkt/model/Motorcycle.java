package ch.wiss.m223.MotoMarkt.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String brand;
    private String model;
    private boolean isSold = false;
    private boolean isBlocked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToMany(mappedBy = "favoriteMotorcycles")
    private Set<User> likedByUsers = new HashSet<>();

    public Motorcycle() {}

    public Motorcycle(String title, String description, String brand, String model, User seller) {
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.seller = seller;
    }
}
