package ch.wiss.m223.MotoMarkt.controller;

import ch.wiss.m223.MotoMarkt.DTOs.MotorcycleCreateRequest;
import ch.wiss.m223.MotoMarkt.DTOs.MotorcycleDTO;
import ch.wiss.m223.MotoMarkt.model.Motorcycle;
import ch.wiss.m223.MotoMarkt.model.User;
import ch.wiss.m223.MotoMarkt.repository.UserRepository;
import ch.wiss.m223.MotoMarkt.security.UserDetailsImpl;
import ch.wiss.m223.MotoMarkt.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/motorcycles")
@CrossOrigin
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @Autowired
    private UserRepository userRepository;


    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetailsImpl) auth.getPrincipal()).getUsername();
        return userRepository.findByUsername(username).orElseThrow();
    }

    @GetMapping("/public")
    public List<MotorcycleDTO> getAll() {
        return motorcycleService.getAllVisibleMotorcycles();
    }

    @GetMapping("/mine")
    public List<MotorcycleDTO> getMyMotorcycles() {
        return motorcycleService.getMyMotorcycles(getCurrentUser());
    }

    @PostMapping
    public ResponseEntity<MotorcycleDTO> create(@RequestBody MotorcycleCreateRequest request) {
        return ResponseEntity.ok().body(motorcycleService.createMotorcycle(request, getCurrentUser()));
}

    @PutMapping("/{id}")
    public MotorcycleDTO update(@PathVariable Long id, @RequestBody MotorcycleCreateRequest request) {
        return motorcycleService.updateMotorcycle(id, request, getCurrentUser());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        motorcycleService.deleteMotorcycle(id, getCurrentUser());
    }

    @PatchMapping("/{id}/block")
    public void block(@PathVariable Long id) {
        motorcycleService.blockMotorcycle(id);
    }

    // Favoriten
    @PostMapping("/{id}/favorite")
    public void addFavorite(@PathVariable Long id) {
        motorcycleService.addFavorite(getCurrentUser(), id);
    }

    @DeleteMapping("/{id}/favorite")
    public void removeFavorite(@PathVariable Long id) {
        motorcycleService.removeFavorite(getCurrentUser(), id);
    }

    @GetMapping("/favorites")
    public Set<Motorcycle> getFavorites() {
        return motorcycleService.getFavorites(getCurrentUser());
    }
}
