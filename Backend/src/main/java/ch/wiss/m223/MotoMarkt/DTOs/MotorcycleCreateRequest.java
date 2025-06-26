package ch.wiss.m223.MotoMarkt.DTOs;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class MotorcycleCreateRequest {
    public String title;
    public String description;
    public String brand;
    public String model;
}