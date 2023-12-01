package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findUsuarioByUsernameAndPassword(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.estadoUsuario.abreviatura IN ('USER_ACTIVE', 'USER_BLOCK')")
    List<User> findActivosOrBlocked();
}
