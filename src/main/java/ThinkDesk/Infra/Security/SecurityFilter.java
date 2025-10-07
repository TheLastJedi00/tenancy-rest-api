package ThinkDesk.Infra.Security;

import ThinkDesk.Domain.Repositories.TechnicianRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final TechnicianRepository technicianRepository;

    public SecurityFilter(TokenService tokenService, TechnicianRepository technicianRepository) {
        this.tokenService = tokenService;
        this.technicianRepository = technicianRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recoverToken(request);
        System.out.println("Filter internal");
        if(tokenJWT != null) {
            System.out.println("Token valid");
            var subject = tokenService.getSubject(tokenJWT);
            UserDetails user = technicianRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        System.out.println("Token null");
        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null) return null;
        return header.replace("Bearer ", "");
    }
}
