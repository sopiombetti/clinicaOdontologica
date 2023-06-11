package com.example.clinicaOdontologicaFinal.controller;

import com.example.clinicaOdontologicaFinal.entity.Odontologo;
import com.example.clinicaOdontologicaFinal.entity.Paciente;
import com.example.clinicaOdontologicaFinal.entity.Turno;
import com.example.clinicaOdontologicaFinal.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se elimino el odontologo con id: " + id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontologo con id: " + id + " no existe");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){

        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(odontologo.getId());
        if(odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado" + " -" + odontologo.getNombre());
        }
        else{
            return  ResponseEntity.badRequest().body("no se pudo actualizar el odontologo "+odontologo.getId()+" -"+odontologo.getNombre());
        }
    }
}
