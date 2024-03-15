package com.pinsoft.ticketwebbe.service;

import com.pinsoft.ticketwebbe.dto.UserUpdateRequest;
import com.pinsoft.ticketwebbe.entity.User;
import com.pinsoft.ticketwebbe.exceptions.ApiRequestException;
import com.pinsoft.ticketwebbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        }
        else {
            userRepository.findById(id);
        }
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ApiRequestException("The given id is not exist!");
        }
        else {
            return userRepository.findById(id);
        }

    }
    public User update(UserUpdateRequest userUpdateRequest)  {
        Optional<User> user = userRepository.findById(userUpdateRequest.getId());
        if(user.isPresent()){
            user.get().setUsername(userUpdateRequest.getUsername());
            user.get().setName(userUpdateRequest.getName());
            user.get().setSurname(userUpdateRequest.getSurname());
            user.get().setEmail(userUpdateRequest.getEmail());
            user.get().setPassword(userUpdateRequest.getPassword());
            user.get().setRole(userUpdateRequest.getRole());
            user.get().setGender(userUpdateRequest.getGender());

            return userRepository.save(user.get());
        }
        else {
            throw new ApiRequestException("User id not exist!");
        }
    }
}
