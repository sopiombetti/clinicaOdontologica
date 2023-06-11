package com.example.clinicaOdontologicaFinal.service;

import com.example.clinicaOdontologicaFinal.entity.Odontologo;
import com.example.clinicaOdontologicaFinal.entity.Paciente;
import com.example.clinicaOdontologicaFinal.entity.Turno;
import com.example.clinicaOdontologicaFinal.exceptions.BadRequestException;
import com.example.clinicaOdontologicaFinal.repository.OdontologoRepository;
import com.example.clinicaOdontologicaFinal.repository.PacienteRepository;
import com.example.clinicaOdontologicaFinal.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private OdontologoRepository odontologoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
    }

    public Turno guardarTurno(Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo().getId());
        if(odontologoBuscado.isPresent() && pacienteBuscado.isPresent()){
            turnoRepository.save(turno);
        }
        else{
            throw new BadRequestException("El paciente y el odontologo deben existir en la base de datos para asignar un turno");
        }
        return turno;
    }
    public Optional<Turno> buscarTurno(Long id){

        return turnoRepository.findById(id);
    }
    public void eliminarTurno(Long id){

        turnoRepository.deleteById(id);
    }
    public List<Turno> buscarTurnos(){

        return turnoRepository.findAll();
    }
    public void actualizarTurno(Turno turno){

        turnoRepository.save(turno);
    }
}
