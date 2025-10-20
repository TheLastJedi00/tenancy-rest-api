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
        LocalDateTime resolutionDueDate,
        @NotNull
        TicketCategory ticketType,
        @NotNull
        Long category,
        Long technician,
        @NotNull
        Long tenant,
        @NotNull
        Long requester,
        @NotNull
        Long priority
){}
