package ThinkDesk.Domain.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ServiceRequest {
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Tenant tenant;
}
