package com.pinsoft.ticketwebbe.controller;

import com.pinsoft.ticketwebbe.dto.AuthenticationRequest;
import com.pinsoft.ticketwebbe.dto.AuthenticationResponse;
import com.pinsoft.ticketwebbe.dto.RegisterRequest;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.service.AuthenticationService;
import com.pinsoft.ticketwebbe.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    @PermitAll
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @GetMapping("/user_account")
    public Collection<User> get(){
        return userService.getAll();
    }

    @GetMapping("/user_account/{id}")
    public User get(@PathVariable Long id){
        Optional<User> optional = userService.getById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
