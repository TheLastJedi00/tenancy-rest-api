package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TicketPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TicketDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        TicketPriority priority,
        LocalDateTime resolutionDueDate

//      Category category,
//      Technician technician,
//      Tenant tenant
){}