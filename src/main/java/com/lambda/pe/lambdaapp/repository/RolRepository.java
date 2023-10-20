package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.nombre = : nombre")
    Role findByNombre(@Param("nombre") String nombre);
}
