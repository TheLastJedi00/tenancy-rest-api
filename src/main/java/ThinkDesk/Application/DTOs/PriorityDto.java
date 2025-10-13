package ThinkDesk.Application.DTOs;

public record PriorityDto(
        String name,
        Long ticketId,
        Long tenantId
        ){}
