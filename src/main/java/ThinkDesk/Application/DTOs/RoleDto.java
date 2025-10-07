package ThinkDesk.Application.DTOs;

import jakarta.validation.constraints.NotBlank;

public record RoleDto(
        @NotBlank
        String role
) {
}
