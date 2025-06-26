package ch.wiss.m223.MotoMarkt.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

@Entity
@Getter 
@Setter 
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    private String password;
    private String email; 
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    } 

    @ManyToMany(fetch = jakarta.persistence.FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "favorites",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "motorcycle_id")
    )
    private Set<Motorcycle> favoriteMotorcycles = new HashSet<>();
}

