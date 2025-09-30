package api_rest.zoologico.Application.DTOs;

import api_rest.zoologico.Domain.Models.Enums.TicketPriority;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TicketDTO(
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        LocalDateTime createdAt,
        @NotNull
        LocalDateTime closedAt,
        @NotNull
        TicketPriority priority,
        @NotNull
        LocalDateTime resolutionDueDate
//      Category category,
//      Technician technician,
//      Tenant tenant
){}