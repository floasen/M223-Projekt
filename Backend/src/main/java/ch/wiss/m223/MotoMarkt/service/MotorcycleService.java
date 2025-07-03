package ch.wiss.m223.MotoMarkt.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import ch.wiss.m223.MotoMarkt.DTOs.MotorcycleCreateRequest;
import ch.wiss.m223.MotoMarkt.DTOs.MotorcycleDTO;
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

    public List<MotorcycleDTO> getAllVisibleMotorcycles() {
        return motorcycleRepository.findByIsBlockedFalse().stream().map(this::toDto).toList();
    }

    public List<MotorcycleDTO> getMyMotorcycles(User currentUser) {
        return motorcycleRepository.findBySeller(currentUser).stream().map(this::toDto).toList();
    }

    public Motorcycle getMotorcycleById(Long id) {
    return motorcycleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Motorcycle not found"));
    }


    @Transactional // wichtig, da neue Entity + Beziehung gespeichert werden
    public MotorcycleDTO createMotorcycle(MotorcycleCreateRequest request, User currentUser) {
        Motorcycle motorcycle = new Motorcycle(
                request.title,
                request.description,
                request.brand,
                request.model,
                currentUser
        ); 
            Motorcycle saveMotorcycle = motorcycleRepository.save(motorcycle);
            return toDto(saveMotorcycle);
    }

    @Transactional
    public MotorcycleDTO updateMotorcycle(Long id, MotorcycleCreateRequest request, User currentUser) {
        Motorcycle motorcycle = motorcycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (!motorcycle.getSeller().equals(currentUser)) {
            throw new RuntimeException("Access denied");
        }

        motorcycle.setTitle(request.title);
        motorcycle.setDescription(request.description);
        motorcycle.setBrand(request.brand);
        motorcycle.setModel(request.model);

        return toDto(motorcycleRepository.save(motorcycle));
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
