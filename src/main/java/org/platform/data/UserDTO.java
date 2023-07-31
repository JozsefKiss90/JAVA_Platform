package org.platform.data;

import java.util.Date;

public record UserDTO(String username, String email, String password, String role, Date created) {}
