package ThinkDesk.Application.DTOs;

import ThinkDesk.Domain.Models.Role;
import ThinkDesk.Domain.Models.Team;
import ThinkDesk.Domain.Models.Tenant;

public record TechnicianKeysDto (
        Tenant tenant,
        Team team,
        Role role
){
}
