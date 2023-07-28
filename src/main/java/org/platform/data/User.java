package org.platform.data;

import java.util.Date;

public record User(String username, String email, String password, String role, Date created) {}
