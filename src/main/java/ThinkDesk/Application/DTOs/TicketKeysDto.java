package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.*;
import jakarta.validation.constraints.NotNull;

public record TicketKeysDto(
        @NotNull
        Category category,
        @NotNull
        Technician technician,
        @NotNull
        Tenant tenant,
        @NotNull
        User requester,
        @NotNull
        Priority priority
) {
}
