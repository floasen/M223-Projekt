package ch.wiss.m223.MotoMarkt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ch.wiss.m223.MotoMarkt.DTOs.MotorcycleDTO;
import ch.wiss.m223.MotoMarkt.model.Motorcycle;
import ch.wiss.m223.MotoMarkt.model.User;
import ch.wiss.m223.MotoMarkt.security.UserDetailsImpl;
import ch.wiss.m223.MotoMarkt.service.FavoriteService;
import ch.wiss.m223.MotoMarkt.service.MotorcycleService;
import ch.wiss.m223.MotoMarkt.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@CrossOrigin
public class FavoriteController {

    private final UserRepository userRepository;
    private final FavoriteService favoriteService;
    private final MotorcycleService motorcycleService;

    @PostMapping("/{motorcycleId}")
public ResponseEntity<MotorcycleDTO> addFavorite(@AuthenticationPrincipal UserDetailsImpl userDetails,
                        @PathVariable Long motorcycleId) {
    String username = userDetails.getUsername();
    User user = userRepository.findByUsername(username).orElseThrow();
    Motorcycle motorcycle = motorcycleService.getMotorcycleById(motorcycleId);
    MotorcycleDTO motorcycleDTO = favoriteService.addFavorite(user, motorcycle);
    return ResponseEntity.ok(motorcycleDTO);
}

    @DeleteMapping("/{motorcycleId}")
    public void removeFavorite(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               @PathVariable Long motorcycleId) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();
        Motorcycle motorcycle = motorcycleService.getMotorcycleById(motorcycleId);
        favoriteService.removeFavorite(user, motorcycle);
    }


    @GetMapping
    public List<MotorcycleDTO> getFavorites(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();
        return favoriteService.getFavorites(user);
    }
}
