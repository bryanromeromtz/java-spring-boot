package com.cursojava.curso.dao;




import com.cursojava.curso.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void deleteUsuario(int id);

    Usuario createUsuario(Usuario usuario);

    boolean verificarCredenciales(Usuario usuario);
}
