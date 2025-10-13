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
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;
    @Enumerated(EnumType.STRING)
    private TicketCategory category;
    @ManyToOne
    private Technician technician;
<<<<<<< HEAD

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
=======
    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;
    private LocalDateTime resolutionDueDate;
>>>>>>> 13f5ad96bf07adf610ccd2002c42b079177aa24a
}