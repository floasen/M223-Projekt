package ch.wiss.m223.MotoMarkt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.wiss.m223.MotoMarkt.model.Favorite;
import ch.wiss.m223.MotoMarkt.model.Motorcycle;
import ch.wiss.m223.MotoMarkt.model.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);

    Optional<Favorite> findByUserAndMotorcycle(User user, Motorcycle motorcycle);

    boolean existsByUserAndMotorcycle(User user, Motorcycle motorcycle);

    void deleteByUserAndMotorcycle(User user, Motorcycle motorcycle);
}
