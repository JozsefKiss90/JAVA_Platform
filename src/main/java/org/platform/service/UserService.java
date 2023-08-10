package org.platform.service;

import org.platform.model.dto.UserDTO;
import org.platform.model.user.User;
import org.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public UserDTO findUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new UserDTO(user);
        }
        return null;
    }

    public void updateUser(Long id, String username, String email) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            user.setUsername(username);
            user.setEmail(email);
            userRepository.save(user);
        });
    }
}

