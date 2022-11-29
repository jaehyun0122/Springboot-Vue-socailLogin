package com.social_example_back.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social_example_back.api.user.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, String> {
	MyUser findByEmail(String email);
}
