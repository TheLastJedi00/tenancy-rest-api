package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record TicketLogDTO(
        @NotBlank
        List<String> content,
        LocalDateTime createdAt,
        @NotBlank
        Long ticket_id,
        @NotNull
        boolean isPrivate
) {
}
