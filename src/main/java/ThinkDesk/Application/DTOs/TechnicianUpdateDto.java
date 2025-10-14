package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TechnicianLevel;

public record TechnicianUpdateDto(
        String name,
        String email,
        String password,
        TechnicianLevel level,
        boolean active,
        Long teamId,
        Long tenantId,
        Long roleId
) {
}
