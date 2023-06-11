package com.example.clinicaOdontologicaFinal.service;

import com.example.clinicaOdontologicaFinal.entity.Domicilio;
import com.example.clinicaOdontologicaFinal.entity.Odontologo;
import com.example.clinicaOdontologicaFinal.entity.Paciente;
import com.example.clinicaOdontologicaFinal.entity.Turno;
import com.example.clinicaOdontologicaFinal.exceptions.BadRequestException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    PacienteService pacienteService;
    @Autowired
    TurnoService turnoService;

    @Test
    @Order(1)
    public void guardarTurno() throws BadRequestException {

        Odontologo odontologo1 = new Odontologo("abc123","Pablo", "Perez");
        Domicilio domicilio1 = new Domicilio("calle3", 1234, "Olivos", "Buenos Aires");
        Paciente paciente1 =new Paciente("Lucia", "Gomez", "gg55", LocalDate.parse("2023-04-07"), domicilio1, "lucia@gmail.com");

        Turno turno1 = new Turno(paciente1, odontologo1, LocalDate.of(2023, 5, 1));

        pacienteService.guardarPaciente(paciente1);

        turnoService.guardarTurno(turno1);

        Assertions.assertEquals(1L, paciente1.getId());

    }

    @Test
    @Order(2)
    public void buscarTurnoPorId() throws BadRequestException {

        Odontologo odontologo2 = new Odontologo("hh66","Juan", "Torres");
        Domicilio domicilio2 = new Domicilio("calle55", 345, "Buenos Aires", "Buenos Aires");
        Paciente paciente2 =new Paciente("Pedro", "Pascal", "mm77", LocalDate.parse("2023-04-06"), domicilio2, "pedro@gmail.com");

        Turno turno2 = new Turno(paciente2, odontologo2, LocalDate.of(2023, 5, 2));

        pacienteService.guardarPaciente(paciente2);

        turnoService.guardarTurno(turno2);

        Optional<Turno> turnoBuscado = turnoService.buscarTurno(2L);

        Assertions.assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    public void listarTurnos(){

        List<Turno> turnos = turnoService.buscarTurnos();

        Assertions.assertEquals(2, turnos.size());


    }

    @Test
    @Order(4)
    public void eliminarTurno(){

        turnoService.eliminarTurno(1L);
        Assertions.assertNull(turnoService.buscarTurno(1L));

    }

}
