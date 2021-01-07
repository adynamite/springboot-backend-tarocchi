package com.arcana.controller;

import lombok.AllArgsConstructor;
import com.arcana.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arcana.dto.*;
import com.arcana.model.User;
import com.arcana.repository.UserRepository;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

import java.net.MalformedURLException;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;


    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws MalformedURLException {
        authService.signup(registerRequest);

        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
    
    @GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
    
    @GetMapping(path="/{email}")
   	public @ResponseBody Optional<User> getAllUsersByEmail(@PathVariable("email") String email) {
   		// This returns a JSON or XML with the users
   		return userRepository.findByEmail(email);
   	}
}
