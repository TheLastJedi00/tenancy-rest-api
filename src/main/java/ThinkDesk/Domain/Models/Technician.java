package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TechnicianDTO;
import ThinkDesk.Application.DTOs.TechnicianKeysDto;
import ThinkDesk.Application.DTOs.TechnicianUpdateDto;
import ThinkDesk.Domain.Models.Enums.TechnicianLevel;
import ThinkDesk.Domain.Services.TechnicianService;
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

    public Technician(TechnicianDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.level = dto.level();
        this.active = dto.active();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
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

    public Technician update(TechnicianUpdateDto dto, TechnicianKeysDto keys) {
        if(dto.name() != null) this.name = dto.name();
        if(dto.email() != null) this.email = dto.email();
        if(dto.password() != null) this.password = dto.password();
        if(dto.level() != null) this.level = dto.level();
        this.active = dto.active();
        if(keys.team() != null) this.team = keys.team();
        if(keys.tenant() != null) this.tenant = keys.tenant();
        if(keys.role() != null) this.roles = Set.of(keys.role());
        return this;
    }
}
