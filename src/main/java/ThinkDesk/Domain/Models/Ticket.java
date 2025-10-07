package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private TicketStatus status;
    private TicketPriority priority;
    @ManyToOne
    private Technician technician;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;
    private LocalDateTime resolutionDueDate;
}