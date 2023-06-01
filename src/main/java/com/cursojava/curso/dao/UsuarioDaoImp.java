package com.cursojava.curso.dao;


import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        List<Usuario> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }

    @Override
    @Transactional
    public void deleteUsuario(int id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

    @Override
    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        return entityManager.merge(usuario);
    }

    @Override
    @Transactional
    public boolean verificarCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();
        if(lista.isEmpty()) {
            return false;
        }
        String passwordHashed = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(passwordHashed, usuario.getPassword());
    }
}

