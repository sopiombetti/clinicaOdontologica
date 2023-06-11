package com.example.clinicaOdontologicaFinal.controller;


import com.example.clinicaOdontologicaFinal.entity.Turno;
import com.example.clinicaOdontologicaFinal.exceptions.BadRequestException;
import com.example.clinicaOdontologicaFinal.service.OdontologoService;
import com.example.clinicaOdontologicaFinal.service.PacienteService;
import com.example.clinicaOdontologicaFinal.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }
    @PostMapping
    public ResponseEntity<String> guardarTurno(@RequestBody Turno turno) throws BadRequestException {
        turnoService.guardarTurno(turno);
        return ResponseEntity.ok("Se guardó el turno correctamente");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino el turno con id: " + id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El turno con id: " + id + " no existe");
        }
    }
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTurnos());
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){

        Optional<Turno> turnoBuscado= turnoService.buscarTurno(turno.getId());
        if(turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado");
        }
        else{
            return  ResponseEntity.badRequest().body("No se pudo actualizar el turno con id:  "+ turno.getId());
        }
    }
}
