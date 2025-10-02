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
        @Positive(message = "O tempo de resposta deve ser um número positivo")
        Integer responseTimeMinutes,

        @NotNull(message = "O tempo de resolução é obrigatório")
        @Positive(message = "O tempo de resolução deve ser um número positivo")
        Integer resolutionTimeMinutes,

        Boolean operationalHoursOnly,

        Boolean isActive,

        @NotNull(message = "A prioridade é obrigatória")
        TicketPriority priority,

        @NotNull(message = "O ID do tenant é obrigatório")
        Long tenantId
) {}