package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TechnicianLevel;
import ThinkDesk.Domain.Models.Tenant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TechnicianDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        TechnicianLevel level,
        @NotNull
        boolean active
) {}