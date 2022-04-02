package com.proyecto.javaweb.Controllers;

import com.proyecto.javaweb.DAO.UsuarioDao;
import com.proyecto.javaweb.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;


    @RequestMapping(value = "api/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        //Usuario user = new Usuario(id,"Jose","Rojas","957731400","joseangel@gmail.com","123456");
        return null;//user;
    }
    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(){
       return usuarioDao.getUsuarios();

    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        usuarioDao.delete(id);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

  /*  @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public void loginUsuario(){

    }*/

}
