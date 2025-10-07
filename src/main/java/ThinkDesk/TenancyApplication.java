package ThinkDesk;

import ThinkDesk.Domain.Models.Technician;
import ThinkDesk.Domain.Repositories.TechnicianRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TenancyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenancyApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(TechnicianRepository technicianRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (technicianRepository.findByEmail("admin") == null) {
                System.out.println("Criando usuário admin inicial...");

                Technician adminUser = new Technician();
                adminUser.setName("Administrador");
                adminUser.setEmail("admin");
                String encodedPassword = passwordEncoder.encode("admin");
                adminUser.setPassword(encodedPassword);

                technicianRepository.save(adminUser);
                System.out.println("Usuário admin criado com sucesso!");
                System.out.println("Senha criptografada: " + encodedPassword);
            }
        };
    }
}
