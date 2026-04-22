package com.poseidon.services;

import com.poseidon.domain.User;
import com.poseidon.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User addUser(User user){
        return this.userRepository.save(user);
    }

    public User updateUser(Integer id, User user){
        User userToUpdate = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found"));
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setFullname(user.getFullname());

        this.userRepository.save(userToUpdate);

        return userToUpdate;
    }

    public User findById(Integer id){
        return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("No User found"));
    }

    public void deleteUser(User user){
        this.userRepository.delete(user);
    }
}
