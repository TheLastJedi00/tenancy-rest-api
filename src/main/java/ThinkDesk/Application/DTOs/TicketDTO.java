package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TicketCategory;
import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.Technician;
import ThinkDesk.Domain.Models.Tenant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TicketDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        LocalDateTime resolutionDueDate,
        @NotBlank
        TicketCategory ticketType,
        @NotBlank
        Long category,
        @NotBlank
        Long technician,
        @NotBlank
        Long tenant,
        @NotBlank
        Long requester,
        @NotBlank
        Long priority
){}