package ch.wiss.m223.MotoMarkt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.wiss.m223.MotoMarkt.model.Role;
import ch.wiss.m223.MotoMarkt.repository.RoleRepository;
import ch.wiss.m223.MotoMarkt.model.ERole;

@SpringBootApplication
public class MotoMarktApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MotoMarktApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
		}
		
	}

}
