package service;

import model.Usuario;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNoEncontradoException;

import java.util.Scanner;

public class PruebaUsuarios {
    public static void ejecutarMenu(GestionUsuarios gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS (PRUEBA CRUD) ---");
            System.out.println("1. Registrar Usuario (Create)");
            System.out.println("2. Listar Usuarios (Read)");
            System.out.println("3. Buscar Usuario por ID (Read)");
            System.out.println("4. Modificar Usuario (Update)");
            System.out.println("5. Eliminar Usuario (Delete)");
            System.out.println("6. Volver / Salir");
            System.out.print("Selecciona una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine()); // Evita errores de escáner
                
                switch (opcion) {
                    case 1:
                        System.out.print("Introduce ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Introduce Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Introduce Correo: ");
                        String correo = scanner.nextLine();
                        
                        gestor.crearUsuario(new Usuario(id, nombre, correo));
                        System.out.println("✅ Usuario creado con éxito.");
                        break;
                        
                    case 2:
                        gestor.listarUsuarios();
                        break;
                        
                    case 3:
                        System.out.print("Introduce el ID a buscar: ");
                        String idBuscar = scanner.nextLine();
                        Usuario buscado = gestor.buscarUsuarioPorId(idBuscar);
                        System.out.println("🔍 Usuario encontrado -> " + buscado);
                        break;
                        
                    case 4:
                        System.out.print("Introduce el ID del usuario a modificar: ");
                        String idMod = scanner.nextLine();
                        System.out.print("Nuevo Nombre: ");
                        String nNombre = scanner.nextLine();
                        System.out.print("Nuevo Correo: ");
                        String nCorreo = scanner.nextLine();
                        
                        gestor.modificarUsuario(idMod, nNombre, nCorreo);
                        System.out.println("✅ Usuario modificado con éxito.");
                        break;
                        
                    case 5:
                        System.out.print("Introduce el ID del usuario a eliminar: ");
                        String idEli = scanner.nextLine();
                        gestor.eliminarUsuario(idEli);
                        System.out.println("❌ Usuario eliminado con éxito.");
                        break;
                        
                    case 6:
                        System.out.println("Saliendo del módulo de usuarios...");
                        break;
                        
                    default:
                        System.out.println("⚠️ Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Por favor, introduce un número válido.");
            } catch (UsuarioDuplicadoException | UsuarioNoEncontradoException e) {
                System.out.println(e.getMessage()); // Muestra el mensaje limpio de tu excepción
            }
        } while (opcion != 6);
    }
}