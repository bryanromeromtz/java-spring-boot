package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {


    @Autowired
    private UsuarioDao usuarioDao;
    @PostMapping(value = "api/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Usuario usuario) {
         boolean credencialesValidas = usuarioDao.verificarCredenciales(usuario);
        if (credencialesValidas) {
            return ResponseEntity.ok("Credenciales válidas"); // Retorna un mensaje de éxito
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas"); // Retorna un mensaje de error
        }
    }
}
