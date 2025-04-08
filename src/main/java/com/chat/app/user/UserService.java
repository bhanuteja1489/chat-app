package com.chat.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final  UserRepository userRepository;

    public boolean checkIfUserExists(String username)
    {
        return userRepository.existsByUsername(username);
    }

    public void addUser(User user)
    {
        userRepository.save(user);
    }



    public Optional<User> getUserFromUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
