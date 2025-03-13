package com.academy.marker.Services;

import com.academy.marker.DTO.UserRequestDTO;
import com.academy.marker.Repos.UserRepository;
import com.academy.marker.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users registerUser(UserRequestDTO userDTO) {
        Users users = new Users();
        users.setUsername(userDTO.getUsername());
        users.setEmail(userDTO.getEmail());
        users.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(users);
    }

    public Optional<Users> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
