package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TechnicianLevel;
import ThinkDesk.Domain.Models.Tenant;
import jakarta.validation.constraints.NotNull;

public record TechnicianDTO(
        @NotNull
        Long id,
        @NotNull
        String name,
        @NotNull
        String email,
        @NotNull
        String password,
        @NotNull
        TechnicianLevel level,
        @NotNull
        boolean active,
        @NotNull
        Tenant tenant
) {}