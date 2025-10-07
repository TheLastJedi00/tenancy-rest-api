package ThinkDesk.Domain.Models;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Application.DTOs.TicketLogDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class TicketLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<String> content;
    private LocalDateTime createdAt;
    @ManyToOne
    private Ticket ticket;
    private boolean isPrivate;

    public TicketLog(TicketLogDTO dto, Ticket ticket) {
        this.content = dto.content();
        this.createdAt = dto.createdAt();
        this.ticket = ticket;
        this.isPrivate = false;
    }
}