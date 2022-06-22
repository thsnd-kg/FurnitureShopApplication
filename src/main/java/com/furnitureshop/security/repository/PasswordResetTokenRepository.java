package com.furnitureshop.security.repository;


import com.furnitureshop.user.entity.PasswordResetToken;
import com.furnitureshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
        Optional<PasswordResetToken> findByToken(String token);
        PasswordResetToken findByUser(User user);

        @Modifying
        @Query(value = "Delete From password_reset_token v where v.user_id = :userId", nativeQuery = true)
        void deleteByUserId(Long userId);
}
