package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Domain.Models.Enums.TicketCategory;
import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String translatedDescription;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;
    @Enumerated(EnumType.STRING)
    private TicketCategory ticketCategory;
    @ManyToOne
    private Technician technician;
    @OneToMany
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;
    private LocalDateTime resolutionDueDate;
}