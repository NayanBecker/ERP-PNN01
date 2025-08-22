package com.nayan.api.users.repository;

import com.nayan.api.users.entities.UsersEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
}
