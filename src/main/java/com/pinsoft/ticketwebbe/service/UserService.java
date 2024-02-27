package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ApiRequestException("The given id is not exist!");
        } else {
            userRepository.findById(id);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ApiRequestException("The given id is not exist!");
        } else {
            return userRepository.findById(id);
        }

    }
}
