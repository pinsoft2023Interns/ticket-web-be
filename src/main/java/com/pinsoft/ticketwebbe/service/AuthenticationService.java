package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.AuthenticationRequest;
import com.pinsoft.ticketwebbe.dto.AuthenticationResponse;
import com.pinsoft.ticketwebbe.dto.RegisterRequest;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.enums.Role;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    private Role role;
    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        if(request.getUsername().isEmpty()||request.getEmail().isEmpty()){
            throw new ApiRequestException("username/email cannot be empty!");
        }else{
            user.setName(request.getName());
            user.setSurname(request.getSurname());
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setRole(request.getRole());
            user.setGender(request.getGender());
            User savedUser = userRepository.save(user);
            var jwtToken = jwtService.generateToken(savedUser);
            return AuthenticationResponse.builder()
                    .token(jwtToken).build();
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user =userRepository.findByUsername(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
}
