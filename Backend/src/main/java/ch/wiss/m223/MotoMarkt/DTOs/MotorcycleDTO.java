package ch.wiss.m223.MotoMarkt.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class MotorcycleDTO {
    public Long id;
    public String title;
    public String description;
    public String brand;
    public String model;
    public double price;
    public String date;
    public int odo;
    public boolean isBlocked;
    public String sellerEmail;
    public String sellerUsername;
}
