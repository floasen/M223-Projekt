package ch.wiss.m223.MotoMarkt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ch.wiss.m223.MotoMarkt.model.Motorcycle;
import ch.wiss.m223.MotoMarkt.model.User;
import org.springframework.stereotype.Repository;
@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    List<Motorcycle> findByIsBlockedFalse();
    List<Motorcycle> findBySeller(User seller);
}