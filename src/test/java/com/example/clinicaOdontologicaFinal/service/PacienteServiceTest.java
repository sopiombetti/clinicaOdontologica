package com.example.clinicaOdontologicaFinal.service;

import com.example.clinicaOdontologicaFinal.entity.Domicilio;
import com.example.clinicaOdontologicaFinal.entity.Odontologo;
import com.example.clinicaOdontologicaFinal.entity.Paciente;
import com.example.clinicaOdontologicaFinal.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    public void testGuardarPaciente(){
        Domicilio domicilio1 = new Domicilio("calle1", 21, "olivos", "bsas");
        Odontologo odontologo1 = new Odontologo("aa11", "Jorge", "Perez");
        Paciente paciente1 = new Paciente("Sofia", "Piombetti", "22bb", LocalDate.parse("2023-04-09"), domicilio1, "sofia@gmail.com");
        pacienteService.guardarPaciente(paciente1);

        Optional<Paciente> pacienteBuscado =  pacienteService.buscarPacientePorCorreo("sofia@gmail.com");

        Assertions.assertNotNull(pacienteBuscado);

    }
    @Test
    @Order(2)
    public void testEliminarPaciente() throws ResourceNotFoundException {

        Domicilio domicilio2 = new Domicilio("calle2", 22, "olivos", "bsas");
        Odontologo odontologo2 = new Odontologo("cc33", "Eduardo", "Gonzalez");
        Paciente paciente2 = new Paciente("Paula", "Piombetti", "dd44", LocalDate.parse("2023-04-08"), domicilio2, "paula@gmail.com");
        pacienteService.guardarPaciente(paciente2);

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorCorreo("paula@gmail.com");


        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(pacienteBuscado.get().getId());
        }

        List<Paciente> pacientes = pacienteService.buscarPacientes();

        Assertions.assertEquals(1, pacientes.size());

    }
    @Test
    @Order(3)
    public void testBuscarPacientes(){

        List<Paciente> pacientes = pacienteService.buscarPacientes();

        Assertions.assertEquals(1, pacientes.size());
    }



}
