package poke_tab.details.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import poke_tab.details.models.User;
import poke_tab.details.models.Login.RequestLogin;
import poke_tab.details.models.Login.ResponseLogin;
import poke_tab.details.repository.UserRepository;

@Service
public class PublicService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    // Register a new user
    public User registerUser(User newUser) {
        if (newUser != null) {
            // Hash the password before saving
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setPoints(100);
            return userRepository.save(newUser);
        }
        return null;
    }

    public ResponseLogin login(RequestLogin requestLogin) {
        // Find the user by username
        User user = userRepository.findByUsername(requestLogin.getUsername());
    
        if (user == null) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
    
        // Validate password
        boolean isPasswordValid = passwordEncoder.matches(requestLogin.getPassword(), user.getPassword());
        if (!isPasswordValid) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
    
        // If the password is valid
        if (!user.isOncesLogin()) {
            user.setOncesLogin(true);
            user.setPoints(user.getPoints() + 50);
            userRepository.save(user); // Persist the updated user
        }
    
        // Construct the response
        ResponseLogin responseLogin = new ResponseLogin();
        responseLogin.setUsername(user.getUsername());
        responseLogin.setRole(user.getRole());
    
        // Return response
        return responseLogin;
    }
    
}
