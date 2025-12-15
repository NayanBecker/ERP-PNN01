package com.pnn.api.users.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pnn.api.users.entities.RoleEntity;
import com.pnn.api.users.entities.UsersEntity;
import com.pnn.api.users.repository.RoleRepository;
import com.pnn.api.users.repository.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(RoleEntity.Values.ADMIN.name());

        System.out.println("Role ADMIN encontrada: " + roleAdmin);
        var userAdmin = userRepository.findByUsername("admin");
        System.out.println("Usuário ADMIN encontrado: " + userAdmin);

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.println("Usuário admin encontrado: " + user.getUsername());
                },
                () -> {
                    var user = new UsersEntity();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                });
    }
}
