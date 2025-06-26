package ch.wiss.m223.MotoMarkt.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import ch.wiss.m223.MotoMarkt.DTOs.MotorcycleCreateRequest;
import ch.wiss.m223.MotoMarkt.model.Motorcycle;
import ch.wiss.m223.MotoMarkt.model.User;
import ch.wiss.m223.MotoMarkt.repository.MotorcycleRepository;
import ch.wiss.m223.MotoMarkt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Motorcycle> getAllVisibleMotorcycles() {
        return motorcycleRepository.findByIsBlockedFalse();
    }

    public List<Motorcycle> getMyMotorcycles(User currentUser) {
        return motorcycleRepository.findBySeller(currentUser);
    }

    @Transactional // wichtig, da neue Entity + Beziehung gespeichert werden
    public Motorcycle createMotorcycle(MotorcycleCreateRequest request, User currentUser) {
        Motorcycle motorcycle = new Motorcycle(
                request.title,
                request.description,
                request.brand,
                request.model,
                currentUser
        );
        return motorcycleRepository.save(motorcycle);
    }

    @Transactional
    public Motorcycle updateMotorcycle(Long id, MotorcycleCreateRequest request, User currentUser) {
        Motorcycle motorcycle = motorcycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (!motorcycle.getSeller().equals(currentUser)) {
            throw new RuntimeException("Access denied");
        }

        motorcycle.setTitle(request.title);
        motorcycle.setDescription(request.description);
        motorcycle.setBrand(request.brand);
        motorcycle.setModel(request.model);

        return motorcycleRepository.save(motorcycle);
    }

    @Transactional
    public void deleteMotorcycle(Long id, User currentUser) {
        Motorcycle motorcycle = motorcycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (!motorcycle.getSeller().equals(currentUser)) {
            throw new RuntimeException("Access denied");
        }

        motorcycleRepository.delete(motorcycle);
    }

    @Transactional
    public void blockMotorcycle(Long id) {
        Motorcycle motorcycle = motorcycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        motorcycle.setBlocked(true);
        motorcycleRepository.save(motorcycle);
    }

    @Transactional
    public void addFavorite(User user, Long motorcycleId) {
        Motorcycle m = motorcycleRepository.findById(motorcycleId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        user.getFavoriteMotorcycles().add(m);
        userRepository.save(user);
    }

    @Transactional
    public void removeFavorite(User user, Long motorcycleId) {
        Motorcycle m = motorcycleRepository.findById(motorcycleId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        user.getFavoriteMotorcycles().remove(m);
        userRepository.save(user);
    }

    public Set<Motorcycle> getFavorites(User user) {
        return user.getFavoriteMotorcycles();
    }
}
