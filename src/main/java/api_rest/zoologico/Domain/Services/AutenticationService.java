package api_rest.zoologico.Domain.Services;

import api_rest.zoologico.Domain.Repositories.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService {
    private final AdminRepository adminRepository;
    public AutenticationService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails admin = adminRepository.findByLogin(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found with login: " + username);
        }
        return admin;
    }
}
