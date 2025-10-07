package ThinkDesk.Application.DTOs;

public record UserRequestDto(
        String name,
        String email,
        String password,
        String position,
        boolean active,
        Long tenantId
)
{}
