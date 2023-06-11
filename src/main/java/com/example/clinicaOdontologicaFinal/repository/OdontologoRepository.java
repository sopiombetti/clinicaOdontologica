package com.example.clinicaOdontologicaFinal.repository;

import com.example.clinicaOdontologicaFinal.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

}
