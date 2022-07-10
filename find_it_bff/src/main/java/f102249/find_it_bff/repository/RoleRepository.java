package f102249.find_it_bff.repository;

import f102249.find_it_bff.models.ERole;
import f102249.find_it_bff.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
