package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.Enums.TicketPriority; // Importe seu Enum
import ThinkDesk.Domain.Models.SlaPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaPolicyRepository extends JpaRepository<SlaPolicy, Long> {

    Page<SlaPolicy> findByTenantId(Long tenantId, Pageable pageable);

    boolean existsByTenantIdAndPriority(Long tenantId, TicketPriority priority);

}