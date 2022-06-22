package com.furnitureshop.security.service;


import com.furnitureshop.security.dto.ForgotPasswordDto;
import com.furnitureshop.user.entity.PasswordResetToken;
import com.furnitureshop.user.entity.User;
import com.furnitureshop.user.entity.VerificationToken;

public interface SecurityUserService {
//    String validatePasswordResetToken(String username, String token);
//
//    String getForgotPasswordToken(String email);
    VerificationToken createVerificationToken(User user);
    Boolean verifyMailToken(String VerificationToken);
    PasswordResetToken createPasswordResetToken(String email);
    void verifyPasswordResetToken(ForgotPasswordDto dto);
    void removeReferenceUser(String username);

}
