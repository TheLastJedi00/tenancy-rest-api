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
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private TicketStatus status;
    private TicketPriority priority;
//    private Category category;
//    private Technician technician;
    private Tenant tenant;
    private LocalDateTime resolutionDueDate;

    public Ticket(TicketDTO dto, TicketStatus status) {
        this.title = dto.title();
        this.description = dto.description();
        this.createdAt = dto.createdAt();
        this.status = status;
        this.priority = dto.priority();
        this.resolutionDueDate = dto.resolutionDueDate();
    }
}