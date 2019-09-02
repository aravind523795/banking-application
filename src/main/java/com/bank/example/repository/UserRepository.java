package com.bank.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.example.entity.UserEntity;

@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, Long> {
 
}