package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.SlaPolicy;

public record SlaPolicyResponseDTO(
        Long id,
        String name,
        Integer responseTimeMinutes,
        Integer resolutionTimeMinutes,
        boolean operationalHoursOnly,
        boolean isActive,
        TicketPriority priority,
        Long tenantId
) {}