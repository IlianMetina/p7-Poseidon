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
}
