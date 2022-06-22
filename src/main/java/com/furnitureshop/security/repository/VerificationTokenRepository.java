package com.furnitureshop.security.repository;


import com.furnitureshop.user.entity.User;
import com.furnitureshop.user.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    VerificationToken findByUser(User user);

    @Modifying
    @Query(value = "Delete From verification_token v where v.user_id = :userId", nativeQuery = true)
    void deleteByUserId(Long userId);
}