package service;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;

import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNoEncontradoException;

public class GestionUsuarios {

    private List<Usuario> listaUsuarios;

    public GestionUsuarios() {
        this.listaUsuarios = new ArrayList<>();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    //CREAR USUARIO
    public void crearUsuario(Usuario nuevoUsuario) {
        for (Usuario u : listaUsuarios) {

            if (u.getId().equalsIgnoreCase(nuevoUsuario.getId())) {
                throw new UsuarioDuplicadoException("Error: Ya existe un usuario con el ID '" + nuevoUsuario.getId() + "'.");
            }
            if (u.getCorreoElectronico().equalsIgnoreCase(nuevoUsuario.getCorreoElectronico())) {
                throw new UsuarioDuplicadoException("Error: Ya existe un usuario registrado con el correo electrónico '" + nuevoUsuario.getCorreoElectronico() + "'.");
            }
        }
        listaUsuarios.add(nuevoUsuario);
    }

    //LISTAR USUARIOS
    public void listarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }
        System.out.println("\n=== LISTADO DE USUARIOS ===");
        for (Usuario u : listaUsuarios) {
            System.out.println(u);
        }
    }

    //BUSCAR POR ID
    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : listaUsuarios) {
            if (u.getId().equalsIgnoreCase(id)) {
                return u;
            }
        }
        throw new UsuarioNoEncontradoException("Error: El usuario con ID '" + id + "' no existe.");
    }

    //MODIFICAR USUARIO
    public void modificarUsuario(String id, String nuevoNombre, String nuevoCorreo) {
    	Usuario usuario = buscarUsuarioPorId(id); 
    	
        for (Usuario u : listaUsuarios) {
            if (!u.getId().equalsIgnoreCase(id) && u.getCorreoElectronico().equalsIgnoreCase(nuevoCorreo)) {
                throw new UsuarioDuplicadoException("Error: No se puede actualizar. El correo '" + nuevoCorreo + "' ya está en uso por otro usuario.");
            }
        }
        
        usuario.setNombre(nuevoNombre);
        usuario.setCorreoElectronico(nuevoCorreo);
    }

    //ELIMINAR USUARIO
    public void eliminarUsuario(String id)  {
        Usuario usuario = buscarUsuarioPorId(id);
        listaUsuarios.remove(usuario);
    }
}


