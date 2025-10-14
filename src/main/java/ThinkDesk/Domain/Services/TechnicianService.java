package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.TechnicianDTO;
import ThinkDesk.Application.DTOs.TechnicianKeysDto;
import ThinkDesk.Application.DTOs.TechnicianUpdateDto;
import ThinkDesk.Domain.Models.Enums.TechnicianLevel;
import ThinkDesk.Domain.Models.Technician;
import ThinkDesk.Domain.Repositories.TechnicianRepository;
import ThinkDesk.Domain.Repositories.TenantRepository;
import ThinkDesk.Infra.Mapper.TechnicianMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final TenantService tenantService;
    private final TeamService teamService;
    private final RoleService roleService;
    private final TechnicianMapper technicianMapper;

    public TechnicianService(TechnicianRepository technicianRepository, TenantService tenantService, TeamService teamService, RoleService roleService, TechnicianMapper technicianMapper) {
        this.technicianRepository = technicianRepository;
        this.tenantService = tenantService;
        this.teamService = teamService;
        this.roleService = roleService;
        this.technicianMapper = technicianMapper;
    }

    public Technician getById(Long id) {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico com ID " + id + " não encontrado."));
    }

    public List<Technician> getByTeamId(Long teamId){
        return technicianRepository.findByTeamId(teamId);
    }

    public Technician update(Long id, TechnicianUpdateDto dto) {
        Technician found = getById(id);
        TechnicianKeysDto keys = new TechnicianKeysDto(
                tenantService.getById(dto.tenantId()),
                teamService.getById(dto.teamId()),
                roleService.getById(dto.roleId())
        );
        Technician technician = found.update(dto, keys);
        return technicianRepository.save(technician);
    }

    public UserDetails getByEmail(String email){
        return technicianRepository.findByEmail(email);
    }

    public void delete(Long id) {
        technicianRepository.deleteById(id);
    }

    public Technician create(TechnicianDTO dto) {
        Technician technician = new Technician(dto);
        return technicianRepository.save(technician);
    }

    public List<Technician> getAll() {
        return technicianRepository.findAll();
    }
}