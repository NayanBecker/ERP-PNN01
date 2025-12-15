package com.pnn.api.users.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pnn.api.users.entities.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {

    Optional<UsersEntity> findByUsername(String username);
}
