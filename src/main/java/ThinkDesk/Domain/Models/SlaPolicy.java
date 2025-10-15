package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Application.DTOs.SlaPolicyKeysDto;
import ThinkDesk.Domain.Models.Enums.TicketCategory;
import ThinkDesk.Domain.Models.Enums.TicketPriority;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SlaPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long responseTimeInMinutes;
    private Long incidentResolutionTimeInMinutes;
    private Long requestFulfillmentTimeInMinutes;
    private boolean operationalHoursOnly = false;
    private boolean isActive = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    public SlaPolicy(SlaPolicyDTO data, SlaPolicyKeysDto keys) {
        this.name = data.name();
        this.responseTimeInMinutes = data.responseTimeMinutes();
        this.incidentResolutionTimeInMinutes = data.resolutionTimeMinutes();
        this.operationalHoursOnly = data.operationalHoursOnly();
        this.isActive = data.isActive();
        this.tenant = keys.tenant();
        this.category = keys.category();
        this.priority = keys.priority();
    }
}