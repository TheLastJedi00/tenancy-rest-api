package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.TechnicianDTO;
import ThinkDesk.Domain.Models.Enums.TechnicianLevel;
import ThinkDesk.Domain.Models.Technician;
import ThinkDesk.Domain.Repositories.TechnicianRepository;
import ThinkDesk.Infra.Mapper.TechnicianMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final TechnicianMapper technicianMapper;

    public TechnicianService(TechnicianRepository technicianRepository, TechnicianMapper technicianMapper) {
        this.technicianRepository = technicianRepository;
        this.technicianMapper = technicianMapper;
    }

    public Technician getById(Long id) {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico com ID " + id + " não encontrado."));
    }

    public Technician update(Long id, TechnicianDTO dto) {
        Technician technician = getById(id);
        technicianMapper.updateEntityFromDto(dto, technician);
        return technicianRepository.save(technician);
    }

    public void delete(Long id) {
        technicianRepository.deleteById(id);
    }

    public Technician create(TechnicianDTO dto) {
        Technician technician = new Technician(dto, TechnicianLevel.L1);
        return technicianRepository.save(technician);
    }

    public List<Technician> getAll() {
        return technicianRepository.findAll();
    }
}