package ThinkDesk.Application.DTOs;

public record UserResponseDto(
        String name,
        String email,
        String position,
        boolean active,
        Long tenantId
) {
}
