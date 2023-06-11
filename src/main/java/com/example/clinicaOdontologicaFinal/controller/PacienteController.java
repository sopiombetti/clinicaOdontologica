package com.example.clinicaOdontologicaFinal.controller;

import com.example.clinicaOdontologicaFinal.entity.Odontologo;
import com.example.clinicaOdontologicaFinal.entity.Paciente;
import com.example.clinicaOdontologicaFinal.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaFinal.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }

        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/buscar/correo/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){

        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado: " + paciente.getNombre());
        }
        else{
            return  ResponseEntity.badRequest().body("No se pudo actualizar el paciente con id:  "+paciente.getId());
        }
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarPacientes());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Se eliminó el paciente con id: "+ id);
    }
}
