package com.example.First_sprint_boot.repository;


import java.util.Optional;

import com.example.First_sprint_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}