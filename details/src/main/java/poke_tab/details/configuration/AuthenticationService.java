package poke_tab.details.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import poke_tab.details.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        poke_tab.details.models.User user = userRepository.findByUsername(username);
                

        // Convert ApplicationUser to Spring Security UserDetails
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
              
                .build();
    }
}
