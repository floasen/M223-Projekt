package ch.wiss.m223.MotoMarkt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.wiss.m223.MotoMarkt.DTOs.MotorcycleDTO;
import ch.wiss.m223.MotoMarkt.model.Favorite;
import ch.wiss.m223.MotoMarkt.model.Motorcycle;
import ch.wiss.m223.MotoMarkt.model.User;
import ch.wiss.m223.MotoMarkt.repository.FavoriteRepository;
import ch.wiss.m223.MotoMarkt.repository.MotorcycleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    @Autowired
    private final MotorcycleRepository motorcycleRepository;
    private final FavoriteRepository favoriteRepository;

    @Transactional
    public MotorcycleDTO addFavorite(User user, Motorcycle motorcycle) {
        if (!favoriteRepository.existsByUserAndMotorcycle(user, motorcycle)) {
            Favorite favorite = new Favorite(null, user, motorcycle);
            favoriteRepository.save(favorite);
        }
        return toDto(motorcycle);
    }

    @Transactional
    public MotorcycleDTO removeFavorite(User user, Motorcycle motorcycle) {
        favoriteRepository.deleteByUserAndMotorcycle(user, motorcycle);
        return toDto(motorcycle);
    }

    public List<MotorcycleDTO> getFavorites(User user) {
        return favoriteRepository.findByUser(user)
            .stream()
            .map(Favorite::getMotorcycle)
            .map(this::toDto)
            .toList();
    }

        private MotorcycleDTO toDto(Motorcycle motorcycle){
        MotorcycleDTO motorcycleDTO = new MotorcycleDTO();
        motorcycleDTO.setId(motorcycle.getId());
        motorcycleDTO.setTitle(motorcycle.getTitle());
        motorcycleDTO.setDescription(motorcycle.getDescription());
        motorcycleDTO.setBrand(motorcycle.getBrand());
        motorcycleDTO.setModel(motorcycle.getModel());  
        motorcycleDTO.setSellerUsername(motorcycle.getSeller().getUsername());
        motorcycleRepository.save(motorcycle);  
        return motorcycleDTO;
    }
}
