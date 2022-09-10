package f102249.find_it_bff.repository;

import f102249.find_it_bff.models.SearchEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<SearchEvent, Long> {
}
