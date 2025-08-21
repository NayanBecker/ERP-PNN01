package com.nayan.api.users.repository;

import com.nayan.api.users.entities.UsersEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
}
