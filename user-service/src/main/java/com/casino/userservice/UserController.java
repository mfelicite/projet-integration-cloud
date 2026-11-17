package com.casino.userservice;

import com.casino.userservice.dto.LoginRequestDto;
import com.casino.userservice.dto.LoginResponseDto;
import com.casino.userservice.dto.RegisterRequestDto;
import com.casino.userservice.dto.RegisterResponseDto;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController 
@RequestMapping("/api/users")
public class UserController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDto register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername((registerRequestDto.username()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ce nom d'utilisateur est déjà utilisé");
        }
        
        User user = new User(
          registerRequestDto.username(),
              passwordEncoder.encode(registerRequestDto.password())
        );
        
        User savedUser = userRepository.save(user);
        
        return new RegisterResponseDto.Builder()
            .id(savedUser.getId())
            .username(savedUser.getUsername())
            .build();
    }
    
    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.username())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));
            
        if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
        }
            
        return new LoginResponseDto.Builder()
            .id(user.getId())
            .username(user.getUsername())
            .build();
    }
}
