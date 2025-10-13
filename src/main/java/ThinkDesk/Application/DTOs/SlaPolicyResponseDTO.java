package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Category;
import ThinkDesk.Domain.Models.Enums.TicketPriority;
import ThinkDesk.Domain.Models.Priority;
import ThinkDesk.Domain.Models.SlaPolicy;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.TenancyApplication;

public record SlaPolicyResponseDTO(
        Long id,
        String name,
        Integer responseTimeMinutes,
        Integer resolutionTimeMinutes,
        boolean operationalHoursOnly,
        boolean isActive,
        Category category,
        Priority priority,
        Tenant tenantId
) {}