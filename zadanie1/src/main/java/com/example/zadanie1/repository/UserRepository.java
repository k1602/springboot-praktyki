package com.example.zadanie1.repository;

import com.example.zadanie1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findById(long id);
}
