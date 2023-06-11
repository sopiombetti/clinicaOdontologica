package com.example.clinicaOdontologicaFinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String cedula;
    @Column
    private LocalDate fechaDeIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id",referencedColumnName = "id")
    private Domicilio domicilio;
    @Column()
    private String email;
    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Paciente(String nombre, String apellido, String cedula, LocalDate fechaDeIngreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    /*public Paciente(Long id, String nombre, String apellido, String cedula, LocalDate fechaDeIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }*/

    public Paciente(){

    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
