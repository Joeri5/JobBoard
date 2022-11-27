package com.github.joeri5.jobboard.user;

import com.github.joeri5.jobboard.security.jwt.JwtTokenUtil;
import com.github.joeri5.jobboard.security.jwt.model.AuthRequest;
import com.github.joeri5.jobboard.security.jwt.model.AuthResponse;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public User registerUser(PasswordEncoder passwordEncoder, User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public @Nullable AuthResponse loginUser(AuthenticationManager authenticationManager, AuthRequest authRequest) {
        String email = authRequest.getEmail(), password = authRequest.getPassword();

        boolean success = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password))
                .isAuthenticated();
        if (!success) {
            return null;
        }

        User user = userRepository.findByEmail(email);
        String token = jwtTokenUtil.generateJwt(user);

        return new AuthResponse(token);
    }

}
