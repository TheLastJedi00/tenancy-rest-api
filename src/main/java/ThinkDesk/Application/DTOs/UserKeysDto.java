package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Role;
import ThinkDesk.Domain.Models.Tenant;

public record UserKeysDto(
        Tenant tenant,
        Role role
) {
}
