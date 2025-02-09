package com.nuketree3.example.mvckp.model.user;

import jakarta.transaction.Transactional;
import org.hibernate.dialect.LobMergeStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT user_role FROM roles WHERE user_id = :id", nativeQuery = true)
    String getUserRole(@Param("id") Long id);

    @Query(value = "SELECT activation_code FROM roles WHERE user_id = :id", nativeQuery = true)
    String getUserActivationCode(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE roles SET user_role = :role WHERE user_id = :id", nativeQuery = true)
    void setUserRole(@Param("id") Long id, @Param("role") String role);

    @Modifying
    @Transactional
    @Query(value = "UPDATE roles SET activation_code = :activationCode WHERE user_id = :id", nativeQuery = true)
    void setUserActivationCode(@Param("id") Long id, @Param("activationCode") String activationCode);

    @Query(value = "SELECT user_id FROM roles WHERE activation_code = :code", nativeQuery = true)
    Long getUserIDByActivationCode(@Param("code") String code);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roles (user_id, user_role, activation_code) VALUES (:id, :role, :code)", nativeQuery = true)
    void setRole(@Param("id") Long id, @Param("role") String role, @Param("code") String code);
}
