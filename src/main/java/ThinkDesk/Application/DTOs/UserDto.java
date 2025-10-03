package ThinkDesk.Application.DTOs;

public record UserDto(
        String name,
        String email,
        String position,
        boolean active,
        Long tenantId
)
{}
