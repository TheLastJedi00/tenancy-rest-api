package ThinkDesk.Domain.Models;

import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.Tenant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class SlaPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer responseTimeMinutes;
    private Integer resolutionTimeMinutes;
    private boolean operationalHoursOnly = false;
    private boolean isActive = true;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority;
    @ManyToOne
    private Tenant tenant;
}