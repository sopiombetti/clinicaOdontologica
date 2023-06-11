package com.example.clinicaOdontologicaFinal.service;

import com.example.clinicaOdontologicaFinal.entity.Odontologo;
import com.example.clinicaOdontologicaFinal.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarOdontologos(){

        return odontologoRepository.findAll();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
}
