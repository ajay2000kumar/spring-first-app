package com.example.spring_boot_startup.repository;

import com.example.spring_boot_startup.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
