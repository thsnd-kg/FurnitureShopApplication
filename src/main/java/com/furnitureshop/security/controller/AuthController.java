package com.furnitureshop.security.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.mail.service.MailService;
import com.furnitureshop.security.dto.ForgotPasswordDto;
import com.furnitureshop.security.dto.LoginDto;
import com.furnitureshop.security.jwt.JwtUtils;
import com.furnitureshop.security.service.SecurityUserService;
import com.furnitureshop.user.dto.CreateUserDto;
import com.furnitureshop.user.entity.PasswordResetToken;
import com.furnitureshop.user.entity.User;
import com.furnitureshop.user.entity.VerificationToken;
import com.furnitureshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private final MailService mailService;
    private final SecurityUserService securityUserService;

    @Autowired
    private PasswordEncoder encoder;



    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, UserService userService, MailService mailService, SecurityUserService securityUserService) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.mailService = mailService;
        this.securityUserService = securityUserService;
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
            if(user.getActiveFlag() == "N"){
                VerificationToken verificationToken  =securityUserService.createVerificationToken(user);
                mailService.sendVerifyMail(verificationToken.getUser().getEmail(), verificationToken.getToken());
                throw new IllegalStateException("Tài khoản chưa xác thực. Mail xác thực đã được gửi đến hòm thư");
            }

            HashMap<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            // log history - AOP
            return ResponseHandler.getResponse(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
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
            VerificationToken token =securityUserService.createVerificationToken(addedUser);
            mailService.sendVerifyMail(token.getUser().getEmail(), token.getToken());

            return ResponseHandler.getResponse(addedUser, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/confirm-email")
    public Object confirmEmail(@RequestParam("token") String token) {
        try{
            securityUserService.verifyMailToken(token);
            return ResponseHandler.getResponse(HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(path= "/forgot-password")
    public Object forgotPassword(@RequestParam("email") String email) {
        try{
//
//            String token = securityUserService.getForgotPasswordToken(email);
            PasswordResetToken token =securityUserService.createPasswordResetToken(email);
            mailService.sendVerifyResetPassword(token.getUser().getEmail(), token.getToken());

            return ResponseHandler.getResponse(HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/reset-password")
    public Object confirmForgotPassword(@RequestBody ForgotPasswordDto dto) {
        try{
            securityUserService.verifyPasswordResetToken(dto);

            return ResponseHandler.getResponse(HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}")
    public Object deleteUser(@PathVariable("username") String username) {
        try{

            securityUserService.removeReferenceUser(username);

            return ResponseHandler.getResponse(HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
