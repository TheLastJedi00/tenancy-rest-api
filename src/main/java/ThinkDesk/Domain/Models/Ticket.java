package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Application.DTOs.TicketKeysDto;
import ThinkDesk.Application.DTOs.TicketUpdateDto;
import ThinkDesk.Domain.Models.Enums.TicketCategory;
import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String translatedDescription;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private LocalDateTime resolutionDueDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @Enumerated(EnumType.STRING)
    private TicketCategory ticketType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id")
    private Technician technician;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User requester;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sla_policy_id")
    private SlaPolicy slaPolicy;

    public Ticket(TicketDTO data, TicketKeysDto keys, SlaPolicy slaPolicy) {
        this.title = data.title();
        this.description = data.description();
        this.createdAt = LocalDateTime.now();
        this.status = TicketStatus.OPEN;
        this.ticketType = data.ticketType();
        this.category = keys.category();
        this.technician = keys.technician();
        this.priority = keys.priority();
        this.tenant = keys.tenant();
        this.requester = keys.requester();
        this.slaPolicy = slaPolicy;
    }

    public Ticket update(TicketUpdateDto data, TicketKeysDto keys) {
        if(data.title() != null) this.title = data.title();
        if(data.description() != null) this.description = data.description();
        if(data.status() != null) this.status = data.status();
        if(data.ticketType() != null) this.ticketType = data.ticketType();
        if(data.closedAt() != null) this.closedAt = data.closedAt();
        if(data.category() != null) this.category = keys.category();
        if(data.technician() != null) this.technician = keys.technician();
        if(data.priority() != null) this.priority = keys.priority();
        if(data.tenant() != null) this.tenant = keys.tenant();
        if(data.requester() != null) this.requester = keys.requester();
        return this;
    }

}