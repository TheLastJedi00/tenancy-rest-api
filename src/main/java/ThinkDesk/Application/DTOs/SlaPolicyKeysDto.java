package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Category;
import ThinkDesk.Domain.Models.Priority;
import ThinkDesk.Domain.Models.Tenant;

public record SlaPolicyKeysDto(
        Tenant tenant,
        Category category,
        Priority priority
) {
}
