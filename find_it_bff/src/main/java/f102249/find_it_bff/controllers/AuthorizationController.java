package f102249.find_it_bff.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @PostMapping("/auto-login")
    public ResponseEntity<?> autoLogin(HttpServletRequest request) {
        return ResponseEntity.ok(null);
    }
}
