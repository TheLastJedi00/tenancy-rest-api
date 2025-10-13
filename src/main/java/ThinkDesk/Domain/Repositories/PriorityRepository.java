package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long>
{
}
