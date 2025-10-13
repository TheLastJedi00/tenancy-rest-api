package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TicketCategory;
import ThinkDesk.Domain.Models.Enums.TicketStatus;

import java.time.LocalDateTime;

public record TicketUpdateDto(
        String title,
        String description,
        LocalDateTime resolutionDueDate,
        TicketCategory ticketType,
        TicketStatus status,
        LocalDateTime closedAt,
        Long category,
        Long technician,
        Long tenant,
        Long requester,
        Long priority
) {
}
