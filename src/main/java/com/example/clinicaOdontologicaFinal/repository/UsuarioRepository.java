package com.example.clinicaOdontologicaFinal.repository;

import com.example.clinicaOdontologicaFinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String correo);
}