package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TechnicianDTO;
import ThinkDesk.Domain.Models.Enums.TechnicianLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Technician implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private TechnicianLevel level;
    private boolean active = true;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "technician_roles",
            joinColumns = @JoinColumn(name = "technician_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    @ManyToOne
    private Team team;

    public Technician(TechnicianDTO dto, TechnicianLevel status) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.level = status;
        this.active = dto.active();
        this.tenant = dto.tenant();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }
    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }
    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
