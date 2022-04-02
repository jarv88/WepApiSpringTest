package com.proyecto.javaweb.DAO;

import com.proyecto.javaweb.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query ="FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void delete(Long id) {
        //Forma 1
        Usuario user = entityManager.find(Usuario.class, id);
        entityManager.remove(user);
    }

    @Override
    public void registrar(Usuario usuario) {
    entityManager.merge(usuario); // hacer un insert
    }

    @Override
    public boolean verificarCredenciales(Usuario usuario) {
        String query ="FROM Usuario where email= :email";
        List<Usuario> user = entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();

        if (user.isEmpty()){
            return false;
        }
        String passwordHash = user.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(passwordHash,usuario.getPassword());

    }

}
