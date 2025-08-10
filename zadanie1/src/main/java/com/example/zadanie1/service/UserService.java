package com.example.zadanie1.service;

import com.example.zadanie1.entity.User;
import com.example.zadanie1.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
     public UserService(UserRepository userRepository) {
         this.userRepository = userRepository;
     }
     public User getUserByEmail(String email){
         return userRepository.findByEmail(email);
     }
     public User createUser(User user){
         validateUser(user);
         return userRepository.save(user);
     }
     public User deleteUserById(Long id){
         User user = userRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("User o ID " + id + " nie istnieje"));
         userRepository.delete(user);
         return user;
     }
    public User updateUser(User user) {
        validateUser(user);
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("Nie można zaktualizować — user nie istnieje");
        }
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
     private void validateUser(User user) {
        if (!StringUtils.hasText(user.getEmail())) {
            throw new IllegalArgumentException("Email nie może być pusty");
        }
     }
}
