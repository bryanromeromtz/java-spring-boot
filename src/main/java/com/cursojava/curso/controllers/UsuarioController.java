package com.cursojava.curso.controllers;


import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        return usuario;
    }

    @GetMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
        if(!this.validarTokenJwt(token)) {
            return null;
        }
        return usuarioDao.getUsuarios();
    }

    public boolean validarTokenJwt(String token) {
        String idUsuario = jwtUtil.getKey(token);
        return idUsuario != null;
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
    public void  deleteUsuario(@PathVariable int id, @RequestHeader(value="Authorization") String token) {
        if(!this.validarTokenJwt(token)) { return;}
        usuarioDao.deleteUsuario(id);
    }



    @PostMapping(value = "api/usuarios")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(2, 1024, 2, usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioDao.createUsuario(usuario);
    }

}
