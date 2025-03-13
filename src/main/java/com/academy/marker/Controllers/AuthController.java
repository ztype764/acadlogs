package com.academy.marker.Controllers;

import com.academy.marker.DTO.UserRequestDTO;
import com.academy.marker.Repos.UserRepository;
import com.academy.marker.entity.Users;
import com.academy.marker.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public Users register(@ModelAttribute UserRequestDTO userDTO) {
        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Users.Role.valueOf(userDTO.getRole()));

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequestDTO loginDTO) {
        Optional<Users> userOpt = userRepository.findByEmail(loginDTO.getEmail());
        if (userOpt.isEmpty() || !passwordEncoder.matches(loginDTO.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        Users user = userOpt.get();
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return Map.of("token", token);
    }
}
