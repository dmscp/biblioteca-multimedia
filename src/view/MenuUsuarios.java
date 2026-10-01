package view;

import service.GestionUsuarios;
import model.Usuario;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNoEncontradoException;
import java.util.Scanner;

public class MenuUsuarios {
    public static void ejecutarMenu(GestionUsuarios gestor) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Buscar usuario por ID");
            System.out.println("4. Modificar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        System.out.print("Introduce nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Introduce correo: ");
                        String correo = scanner.nextLine();
                        gestor.crearUsuario(new Usuario(nombre, correo));
                        System.out.println("Usuario creado con éxito.");
                        break;
                    case 2:
                        gestor.listarUsuarios();
                        break;
                    case 3:
                        System.out.print("Introduce el ID a buscar: ");
                        String idBuscar = scanner.nextLine();
                        System.out.println("Usuario encontrado -> " + gestor.buscarUsuarioPorId(idBuscar));
                        break;
                    case 4:
                        System.out.print("Introduce el ID del usuario a modificar: ");
                        String idMod = scanner.nextLine();
                        try {
                            Usuario uExistente = gestor.buscarUsuarioPorId(idMod);
                            
                            System.out.print("Nuevo nombre [" + uExistente.getNombre() + "]: ");
                            String nNombre = scanner.nextLine();
                            System.out.print("Nuevo correo [" + uExistente.getCorreoElectronico() + "]: ");
                            String nCorreo = scanner.nextLine();
                            
                            gestor.modificarUsuario(idMod, nNombre, nCorreo);
                            System.out.println("Usuario modificado con éxito.");
                        } catch (UsuarioNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        } catch (UsuarioDuplicadoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("Introduce el ID del usuario a eliminar: ");
                        String idEli = scanner.nextLine();
                        gestor.eliminarUsuario(idEli);
                        System.out.println("Usuario eliminado con éxito.");
                        break;
                    case 6:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            } catch (UsuarioDuplicadoException | UsuarioNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != 6);
    }
}
