package poke_tab.details.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import poke_tab.details.models.Rating;
import poke_tab.details.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-rating/{name}")
    public ResponseEntity<?> addRating(@PathVariable String name, @RequestBody Rating rating) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
            // Check if user is authenticated
            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName(); // Extract username from auth context
                rating.setUsername(username); // Set username in Rating object
    
                // Call service to handle adding the rating
                Rating addedRating = userService.addRating(username, name, rating);
    
                if (addedRating != null) {
                    return ResponseEntity.ok(addedRating);
                } else {
                    return ResponseEntity.status(404).body("Already rated or unable to add rating.");
                }
            } else {
                return ResponseEntity.status(401).body("Unauthorized access. Please log in.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
            return ResponseEntity.status(500).body("An internal error occurred. Please try again later.");
        }
    }


  
    
}
