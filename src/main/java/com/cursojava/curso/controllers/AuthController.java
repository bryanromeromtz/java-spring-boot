package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {


    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "api/login")
    public String iniciarSesion(@RequestBody Usuario usuario) {
         boolean credencialesValidas = usuarioDao.verificarCredenciales(usuario);
        if (credencialesValidas) {
            return "OK";
        } else {
            return "FAIL";
        }
    }
}
