package ch.wiss.m223.MotoMarkt.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cascade;

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
    private double price;
    private String date;
    private int odo;
    private boolean isSold;
    private boolean isBlocked;
    private String sellerEmail;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    public Motorcycle() {}

    public Motorcycle(String title, String description, String brand, String model, double price, String date, int odo, String sellerEmail, User seller) {
        this.title = title;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.date = date;
        this.odo = odo;
        this.sellerEmail = sellerEmail;
        this.seller = seller;
        
        
    }
}
