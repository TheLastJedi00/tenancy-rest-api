package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TechnicianDTO;
import ThinkDesk.Domain.Models.Enums.TechnicianLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private TechnicianLevel level;
    private boolean active = true;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;

    public Technician(TechnicianDTO dto, TechnicianLevel status) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.level = level;
        this.active = dto.active();
        this.tenant = dto.tenant();
    }
}
