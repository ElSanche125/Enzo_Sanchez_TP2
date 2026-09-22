package com.example.aydsII.act4.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.email = :email")
    boolean existeEmail(@Param("email") String email);
}