package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Tenant getReferenceByTaxID(String s);
}
