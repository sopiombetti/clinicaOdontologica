package com.example.clinicaOdontologicaFinal.service;

import com.example.clinicaOdontologicaFinal.entity.Paciente;
import com.example.clinicaOdontologicaFinal.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaFinal.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;
    private static final Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {

        this.pacienteRepository = pacienteRepository;
    }
    public Paciente guardarPaciente(Paciente paciente){
        logger.info("Registrando paciente nuevo");
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){
        logger.info("Buscando paciente por id");
        return pacienteRepository.findById(id);
    }
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("No se puede eliminar el paciente con id: "+ id);
        }

    }
    public void actualizarPaciente(Paciente paciente){
        logger.info("Actualizando paciente");
        pacienteRepository.save(paciente);
    }
    public List<Paciente> buscarPacientes(){

        return pacienteRepository.findAll();
    }
    public Optional<Paciente>buscarPacientePorCorreo(String correo){

        return pacienteRepository.findByEmail(correo);
    }
}
