package f102249.find_it_bff.controllers;

import f102249.find_it_bff.models.place.Place;
import f102249.find_it_bff.payload.request.place.AddPlaceRequest;
import f102249.find_it_bff.payload.response.PlaceResponse;
import f102249.find_it_bff.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/places")
public class PlacesController {

    @Autowired
    PlaceRepository placeRepository;

    @GetMapping("/all")
    public ResponseEntity<List<PlaceResponse>> getAllPlaces(@RequestParam String city, @RequestParam String placeType, HttpServletRequest request) {
        List<PlaceResponse> placeList = placeRepository.findAllByCity(city).stream()
                .filter(place -> place.getPlaceType().equals(placeType))
                .map(place -> new PlaceResponse(
                        place.getPlaceId(),
                        place.getLat(),
                        place.getLng(),
                        place.getName(),
                        place.getRating(),
                        place.getAddress(),
                        place.getCity(),
                        place.getAccessibleFeatures()
                )).collect(Collectors.toList());

        return ResponseEntity.ok(placeList);
    }

    @PutMapping("/place")
    public ResponseEntity<?> addPlace(@Valid @RequestBody AddPlaceRequest placeRequest, HttpServletRequest request) {
        Optional<Place> foundedPlace = placeRepository.findByPlaceId(placeRequest.getPlaceId());

        if (foundedPlace.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Place place = new Place(placeRequest.getPlaceId(),
                placeRequest.getLat(),
                placeRequest.getLng(),
                placeRequest.getName(),
                placeRequest.getRating(),
                placeRequest.getAddress(),
                placeRequest.getCity(),
                placeRequest.getPlaceType(),
                placeRequest.getAccessibleFeatures());

        placeRepository.save(place);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/place")
    public ResponseEntity<?> deletePlace(@RequestParam String placeId, HttpServletRequest request) {
        Place place = placeRepository.findByPlaceId(placeId).orElseThrow(() -> new RuntimeException("Error: Place is not found."));
        placeRepository.delete(place);
        return ResponseEntity.ok("Deleted");
    }
}
