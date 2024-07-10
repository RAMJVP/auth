package com.secured.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.secured.auth.config.JwtUtil;
import com.secured.auth.service.MyUserDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.secured.auth.model.AuthRequest;
import com.secured.auth.model.AuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    

    @Autowired
    private MyUserDetailsService userDetailsService;
    
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the users",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = AuthResponse.class)))
    })

    @PostMapping("/login")
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                (authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }

        final UserDetails userDetails = userDetailsService.
        		loadUserByUsername(authRequest.getUsername());
        
        final String jwt = jwtUtil.generateToken
        		(userDetails.getUsername());

        return new AuthResponse(jwt);
    }
    
    
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    
    @GetMapping("/encode/{str}")
	public String listRest(@PathVariable("str") String str) {
    	return passwordEncoder.encode(str);
    	//$2a$10$rDDPB6p10FKvhaeTR5sQ9eSUkJNGSC0HdT7HOprYUWPTcP15m7g.O
    	
    }
    
    
    
    @GetMapping("/encode1/{str}")
   	public String userName(@PathVariable("str") String str) {
       	return userDetailsService.loadUserByUsername(str).getPassword();
       	//$2a$10$rDDPB6p10FKvhaeTR5sQ9eSUkJNGSC0HdT7HOprYUWPTcP15m7g.O
       	
       }
}
