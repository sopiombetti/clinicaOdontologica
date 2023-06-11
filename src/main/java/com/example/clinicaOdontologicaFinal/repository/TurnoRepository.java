package com.example.clinicaOdontologicaFinal.repository;

import com.example.clinicaOdontologicaFinal.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

}
