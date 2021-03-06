package com.kms.challenges.rbh.repository;

import com.kms.challenges.rbh.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
