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
) {
    public SlaPolicyResponseDTO(SlaPolicy slaPolicy) {
        this(
                slaPolicy.getId(),
                slaPolicy.getName(),
                slaPolicy.getResponseTimeMinutes(),
                slaPolicy.getResolutionTimeMinutes(),
                slaPolicy.isOperationalHoursOnly(),
                slaPolicy.isActive(),
                slaPolicy.getPriority(),
                slaPolicy.getTenant().getId()
        );
    }
}