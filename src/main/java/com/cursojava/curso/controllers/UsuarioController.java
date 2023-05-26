package com.cursojava.curso.controllers;


import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Madzilla");
        usuario.setApellido("Romero");
        usuario.setEmail("cryptomtz@hotmail.com");
        usuario.setTelefono("5580761942");
        usuario.setPassword("qwerty");
        return usuario;
    }

    @RequestMapping(value = "usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }
    public Usuario editUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Madzilla");
        usuario.setApellido("Romero");
        usuario.setEmail("cryptomtz@hotmail.com");
        usuario.setTelefono("5580761942");
        usuario.setPassword("qwerty");
        return usuario;
    }

    public Usuario deleteUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Madzilla");
        usuario.setApellido("Romero");
        usuario.setEmail("cryptomtz@hotmail.com");
        usuario.setTelefono("5580761942");
        usuario.setPassword("qwerty");
        return usuario;
    }
}
