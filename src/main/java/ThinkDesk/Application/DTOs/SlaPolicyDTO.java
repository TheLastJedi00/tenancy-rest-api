package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Enums.TicketPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SlaPolicyDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(max = 100)
        String name,

        @NotNull(message = "O tempo de resposta é obrigatório")
        @Positive
        Integer responseTimeMinutes,

        @NotNull(message = "O tempo de resolução é obrigatório")
        @Positive
        Integer resolutionTimeMinutes,

        Boolean operationalHoursOnly,
        Boolean isActive,

        @NotNull(message = "A categoria é obrigatória")
        Long categoryId,

        @NotNull(message = "O ID do tenant é obrigatório")
        Long tenantId
){}