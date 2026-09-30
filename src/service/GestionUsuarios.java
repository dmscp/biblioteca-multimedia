package service;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;

import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNoEncontradoException;

public class GestionUsuarios {
    // Colección para almacenar los usuarios en memoria durante la ejecución
    private List<Usuario> listaUsuarios;

    public GestionUsuarios() {
        this.listaUsuarios = new ArrayList<>();
    }

    // Devuelve la lista completa (esencial para los compañeros de préstamos y persistencia)
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    // 1. C - CREAR (Comprueba duplicados antes de insertar)
    public void crearUsuario(Usuario nuevoUsuario) {
        for (Usuario u : listaUsuarios) {
            // Validación por ID (por seguridad)
            if (u.getId().equalsIgnoreCase(nuevoUsuario.getId())) {
                throw new UsuarioDuplicadoException("Error: Ya existe un usuario con el ID '" + nuevoUsuario.getId() + "'.");
            }
            // Nueva Validación: Control de correo electrónico único
            if (u.getCorreoElectronico().equalsIgnoreCase(nuevoUsuario.getCorreoElectronico())) {
                throw new UsuarioDuplicadoException("Error: Ya existe un usuario registrado con el correo electrónico '" + nuevoUsuario.getCorreoElectronico() + "'.");
            }
        }
        listaUsuarios.add(nuevoUsuario);
    }

    // 2. R - LEER / LISTAR
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

    // 3. R - LEER / BUSCAR POR ID
    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : listaUsuarios) {
            if (u.getId().equalsIgnoreCase(id)) {
                return u;
            }
        }
        throw new UsuarioNoEncontradoException("Error: El usuario con ID '" + id + "' no existe.");
    }

    // 4. U - MODIFICAR
    public void modificarUsuario(String id, String nuevoNombre, String nuevoCorreo) {
        // Primero verificamos que el nuevo correo no pertenezca a OTRO usuario distinto
        for (Usuario u : listaUsuarios) {
            if (!u.getId().equalsIgnoreCase(id) && u.getCorreoElectronico().equalsIgnoreCase(nuevoCorreo)) {
                throw new UsuarioDuplicadoException("Error: No se puede actualizar. El correo '" + nuevoCorreo + "' ya está en uso por otro usuario.");
            }
        }
        
        Usuario usuario = buscarUsuarioPorId(id); 
        usuario.setNombre(nuevoNombre);
        usuario.setCorreoElectronico(nuevoCorreo);
    }

    // 5. D - ELIMINAR
    public void eliminarUsuario(String id)  {
        Usuario usuario = buscarUsuarioPorId(id);
        listaUsuarios.remove(usuario);
    }
}


