package ThinkDesk;

import ThinkDesk.Domain.Models.Admin;
import ThinkDesk.Domain.Repositories.AdminRepository;
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
    CommandLineRunner initDatabase(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (adminRepository.findByLogin("admin") == null) {
                System.out.println("Criando usuário admin inicial...");

                Admin adminUser = new Admin();
                adminUser.setName("Administrador");
                adminUser.setLogin("admin");
                String encodedPassword = passwordEncoder.encode("admin");
                adminUser.setPassword(encodedPassword);

                adminRepository.save(adminUser);
                System.out.println("Usuário admin criado com sucesso!");
                System.out.println("Senha criptografada: " + encodedPassword);
            }
        };
    }
}
