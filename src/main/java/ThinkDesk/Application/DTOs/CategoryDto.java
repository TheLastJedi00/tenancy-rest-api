package ThinkDesk.Application.DTOs;

public record CategoryDto(
        String name,
        String description,
        Long tenantId
) {
}
