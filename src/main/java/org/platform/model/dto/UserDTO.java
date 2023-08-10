package org.platform.model.dto;

import org.platform.model.user.User;


import java.util.Date;

public record UserDTO(Long id, String username, String email, String password, String role, Date created) {
    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole(), user.getCreated());
    };
}