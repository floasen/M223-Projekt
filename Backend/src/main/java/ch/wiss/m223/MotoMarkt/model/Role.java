package ch.wiss.m223.MotoMarkt.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)

    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public String toString() {
        return name.toString();
    }

    @ManyToMany(fetch = jakarta.persistence.FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();
}
