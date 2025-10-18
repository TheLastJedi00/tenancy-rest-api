package ThinkDesk.Application.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDto(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
