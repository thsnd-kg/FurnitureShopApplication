package com.furnitureshop.security.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.security.dto.LoginDto;
import com.furnitureshop.security.jwt.JwtUtils;
import com.furnitureshop.user.dto.CreateUserDto;
import com.furnitureshop.user.entity.User;
import com.furnitureshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Autowired
    private PasswordEncoder encoder;



    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserService userService) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }


    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            User user = userService.getUserByUsername(dto.getUsername());
            HashMap<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            // log history - AOP
            return ResponseHandler.getResponse(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("{} has been logged in with wrong password: {}" + dto.getUsername() + e.getMessage() );
        }

        return ResponseHandler.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/register")
    public Object register(@Valid @RequestBody CreateUserDto dto, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            dto.setRoleId(null);
            User addedUser = userService.createUser(dto);

            return ResponseHandler.getResponse(addedUser, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
