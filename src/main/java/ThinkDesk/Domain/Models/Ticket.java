package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Application.DTOs.TicketUpdateDto;
import ThinkDesk.Domain.Models.Enums.TicketCategory;
import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String translatedDescription;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime closedAt;

    @Column(nullable = false)
    private LocalDateTime resolutionDueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", nullable = false)
    private TicketCategory ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    public Ticket(TicketDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.resolutionDueDate = data.resolutionDueDate();
        this.status = TicketStatus.OPEN;
        this.ticketType = data.ticketType();
    }

    public void update(TicketUpdateDto data) {
        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.description() != null) {
            this.description = data.description();
        }
        if (data.closedAt() != null) {
            this.closedAt = data.closedAt();
        }
        if (data.resolutionDueDate() != null) {
            this.resolutionDueDate = data.resolutionDueDate();
        }
        if (data.status() != null) {
            this.status = data.status();
        }
        if (data.ticketType() != null) {
            this.ticketType = data.ticketType();
        }
    }
}