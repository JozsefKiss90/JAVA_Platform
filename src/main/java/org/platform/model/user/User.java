package org.platform.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spaceship")
@Transactional
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private Date created;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
