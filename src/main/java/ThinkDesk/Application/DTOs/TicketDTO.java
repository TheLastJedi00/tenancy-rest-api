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
        TicketPriority priority,
        @NotBlank
        LocalDateTime resolutionDueDate,
        @NotBlank
        TicketCategory category,
        @NotBlank
        Technician technician,
        @NotBlank
        Tenant tenant
){}