package f102249.find_it_bff.repository;

import f102249.find_it_bff.models.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    Optional<Place> findByPlaceId(String placeId);

    List<Place> findAllByCity(String city);
}
