package com.example.clinicaOdontologicaFinal.security;

import com.example.clinicaOdontologicaFinal.entity.Usuario;
import com.example.clinicaOdontologicaFinal.entity.UsuarioRole;
import com.example.clinicaOdontologicaFinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesUsuario implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passsSinCifrar= "digital";
        String passCifrado= cifrador.encode(passsSinCifrar);
        Usuario usuarioAInsertar= new Usuario("Sofia","sofiap","sofia@gmail.com",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);

    }
}
