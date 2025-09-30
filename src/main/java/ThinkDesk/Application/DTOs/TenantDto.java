package ThinkDesk.Application.DTOs;

import java.time.LocalDateTime;

public record TenantDto(
        String tradingName, //Nome Fantasia
        String taxID, //CNPJ
        LocalDateTime createdAt,
        String settings //Layout customizations JSON
) {
}
