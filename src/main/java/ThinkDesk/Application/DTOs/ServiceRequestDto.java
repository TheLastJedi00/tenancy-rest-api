package ThinkDesk.Application.DTOs;

public record ServiceRequestDto(
        String name,
        String description,
        Long tenant_id
) {
}
