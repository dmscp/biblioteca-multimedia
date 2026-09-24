package main;

import service.GestionUsuarios;
import service.PruebaUsuarios;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE BIBLIOTECA MULTIMEDIA ===");
        
        // 1. Instanciamos el gestor de usuarios que tú has creado
        GestionUsuarios gestorUsuarios = new GestionUsuarios();
        
        // 2. Llamamos al menú interactivo de pruebas pasándole tu gestor
        PruebaUsuarios.ejecutarMenu(gestorUsuarios);
        
        System.out.println("=== APLICACIÓN FINALIZADA ===");
    }
}
