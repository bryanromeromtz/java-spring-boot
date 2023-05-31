package com.cursojava.curso.controllers;


import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        return usuario;
    }

    @GetMapping(value = "api/usuarios")
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

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@PathVariable int id) {
        usuarioDao.deleteUsuario(id);
    }



    @PostMapping(value = "api/usuarios")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioDao.createUsuario(usuario);
    }

}
