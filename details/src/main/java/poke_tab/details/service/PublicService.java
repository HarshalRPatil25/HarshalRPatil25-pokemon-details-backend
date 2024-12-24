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
            return userRepository.save(newUser);
        }
        return null;
    }

    // Log in a user
    public ResponseLogin login(RequestLogin requestLogin) {
        User user = userRepository.findByUsername(requestLogin.getUsername());

        // User not found
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + requestLogin.getUsername());
        }

        // Password comparison
        boolean isPasswordValid = passwordEncoder.matches(requestLogin.getPassword(), user.getPassword());

        // If the password is valid
        if (isPasswordValid) {
            ResponseLogin responseLogin = new ResponseLogin();
            responseLogin.setUsername(user.getUsername());
            responseLogin.setRole(user.getRole());
            // Do not return the plain password, for security reasons
            return responseLogin;
        }

        // If password doesn't match
        return null;
    }
}
