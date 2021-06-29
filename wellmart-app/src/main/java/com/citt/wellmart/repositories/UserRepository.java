package com.citt.wellmart.repositories;



import com.citt.wellmart.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    List<User> findAllByAccountNonLockedAndLastModifiedDateIsBefore(Boolean locked, Timestamp timestamp);

}
