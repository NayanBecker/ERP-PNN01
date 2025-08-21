
package com.nayan.api.users.entities;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;


    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = fetchType.EAGER)
    @JoinTable(
        name = "tb_users_roles"
        joinColumns = @JoinColumn(name="user_id")
        inverseJoinColumns = @JoinColumn(name="role_id")
    )

    private Set<RoleEntity> roles;

}
