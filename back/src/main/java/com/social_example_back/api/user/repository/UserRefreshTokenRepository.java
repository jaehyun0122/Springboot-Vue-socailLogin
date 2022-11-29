package com.social_example_back.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social_example_back.api.user.entity.UserRefreshToken;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByEmail(String email);
    UserRefreshToken findByEmailAndRefreshToken(String email, String refreshToken);
}
