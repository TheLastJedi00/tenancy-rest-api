package ThinkDesk.Domain.Services;

import ThinkDesk.Domain.Models.Team;
import ThinkDesk.Domain.Repositories.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team com ID " + id + " não encontrado."));
    }
}
