package f102249.find_it_bff.controllers;

import f102249.find_it_bff.models.SearchEvent;
import f102249.find_it_bff.payload.request.event.SearchEventRequest;
import f102249.find_it_bff.repository.TrackingRepository;
import f102249.find_it_bff.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/track")
public class TrackingController {

    @Autowired
    TrackingRepository trackingRepository;

    @PostMapping("event")
    public ResponseEntity<?> trackSearchEvent(@Valid @RequestBody SearchEventRequest searchEventRequest, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userDetails = authentication.getPrincipal();

        SearchEvent searchEvent;
        if (userDetails instanceof UserDetailsImpl) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            int age = calculateAge(LocalDate.parse(((UserDetailsImpl) userDetails).getDateOfBirth(), formatter));
            searchEvent = new SearchEvent(searchEventRequest.getPlaceType(), searchEventRequest.getCity(), age);
        } else {
            searchEvent = new SearchEvent(searchEventRequest.getPlaceType(), searchEventRequest.getCity(), 0);
        }
        trackingRepository.save(searchEvent);
        return ResponseEntity.ok("Success");
    }

    public static int calculateAge(LocalDate dob) {
        LocalDate curDate = LocalDate.now();
        if (dob != null) {
            return Period.between(dob, curDate).getYears();
        } else {
            return 0;
        }
    }
}
