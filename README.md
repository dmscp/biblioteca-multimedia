# Sistema de Gestión de Biblioteca Multimedia

Este proyecto es una aplicación de consola en Java desarrollada como parte de la Práctica 1 para la asignatura de Acceso a Datos (DAM2, Curso 2026-2027). Su objetivo principal es repasar los fundamentos de la Programación Orientada a Objetos y aplicar las buenas prácticas en el control de versiones utilizando Git y GitHub.

## Integrantes del Grupo
* [Allison Hernando](https://github.com/AllisonHernando)
* [Marina Redondo](https://github.com/marredon9)
* [Samuel Correia](https://github.com/dmscp)

## Descripción y Funcionalidades
La aplicación permite administrar de forma integral una biblioteca multimedia mediante las siguientes funciones principales:
* **Gestión de Usuarios:** Operaciones CRUD completas para dar de alta, listar, buscar por ID, modificar y eliminar usuarios del sistema, garantizando la no duplicidad de identificadores mediante el uso de UUIDs.
* **Gestión de Recursos:** Soporte para tres tipos de recursos multimedia (Libros, Películas y Videojuegos) compartiendo una estructura común mediante herencia y abstracción, permitiendo su registro y edición en base a sus propiedades específicas.
* **Sistema de Préstamos y Devoluciones:** Control estricto de disponibilidad que impide el préstamo de recursos ocupados, actualizando los estados correspondientes y enlazando dinámicamente las entidades de usuario y recurso.
* **Consultas Avanzadas:** Filtros de recursos por estado (disponibles/prestados), búsquedas de préstamos activos por títulos, historial de transacciones por usuario y búsquedas específicas por tipo de recurso.
* **Persistencia de Datos:** Carga automática de la información almacenada en ficheros CSV al arrancar la aplicación y volcado estructurado de los datos al seleccionar la opción de salida, asegurando la consistencia entre sesiones.

## Estructura General del Proyecto
El código fuente se encuentra organizado dentro del directorio `src` bajo una arquitectura limpia dividida en los siguientes paquetes:
* `main`: Contiene la clase `Main.java` que actúa como punto de entrada de la aplicación, inicializando los gestores y controlando el bucle del menú principal.
* `model`: Define las entidades de negocio. Incluye la clase abstracta `Recurso`, las subclases `Libro`, `Pelicula` y `Videojuego`, el enumerado `EstadoRecurso`, y las clases `Usuario` y `Prestamo`.
* `service`: Implementa la lógica de control. Contiene las clases encargadas de las operaciones en memoria (`GestionUsuarios`, `GestionRecursos`, `GestionPrestamos`) y las clases de interfaz de consola de prueba (`PruebaUsuarios`, `PruebaRecursos`, `PruebaPrestamos`), además del motor de persistencia `PersistenciaCSV`.
* `exceptions`: Aloja las excepciones personalizadas del sistema encargadas de controlar las reglas de negocio (ej. usuarios no encontrados o recursos duplicados).
* `datos`: Carpeta física interna donde residen los archivos de datos (`usuarios.csv`, `recursos.csv`, `prestamos.csv`).

## Instrucciones para la Ejecución
Para compilar y ejecutar la aplicación de consola, asegúrese de contar con el JDK 17 o superior instalado y siga estos pasos desde la terminal:

1. Clone el repositorio en su máquina local:
   ```bash
   git clone https://github.com
   ```
2. Acceda al directorio raíz del proyecto:
   ```bash
   cd biblioteca-multimedia
   ```
3. Compile los archivos fuente dirigiendo la salida al directorio de clases correspondientes (o bien importe el proyecto directamente en su IDE de preferencia como Eclipse o IntelliJ IDEA):
   ```bash
   javac -d bin src/model/*.java src/exceptions/*.java src/service/*.java src/main/*.java
   ```
4. Ejecute la aplicación:
   ```bash
   java -cp bin main.Main
   ```

## Reparto Inicial del Trabajo
Para cumplir con las pautas de trabajo en equipo y evitar que los integrantes trabajasen aislados, el reparto de tareas se organizó mediante el flujo de ramas de la siguiente manera:
* **Módulo de Usuarios (`feature/usuarios`):** Desarrollado por [Samuel Correia](https://github.com/dmscp), responsable del modelo de usuario, persistencia básica de usuarios y su interfaz CRUD.
* **Módulo de Recursos (`feature/recursos`):** Desarrollado por [Allison Hernando](https://github.com/AllisonHernando), encargado de plantear la herencia de la clase abstracta Recurso, las especializaciones de Libro, Película y Videojuego, y sus mutadores.
* **Módulo de Préstamos (`feature/prestamos`):** Desarrollado por [Marina Redondo](https://github.com/marredon9), responsable del control de flujos de disponibilidad, fechas y vinculación de elementos de la lógica de negocio.
* **Persistencia Integrada y Excepciones (`feature/persistencia`):** Desarrollado por [Samuel Correia](https://github.com/dmscp), encargado de coordinar la lectura/escritura unificada en archivos CSV, la gestión robusta de excepciones del sistema y la corrección de fallos.

## Problemas Relevantes Encontrados y Soluciones
* **Pérdida de Estados en la Persistencia:** Durante las pruebas integradas se detectó que al reiniciar el programa todos los recursos volvían al estado "DISPONIBLE" independientemente de su valor en el archivo. Se solucionó extendiendo el constructor de persistencia y el método de lectura del CSV para almacenar y parsear explícitamente el valor del enum `EstadoRecurso`.
* **Conflictos e Inconsistencias en la Gestión de Identificadores:** En las primeras fases del desarrollo, permitíamos que los usuarios ingresaran manualmente los identificadores únicos (IDs) para recursos y usuarios mediante la consola. Esto provocaba constantes errores humanos, excepciones por identificadores duplicados y fallos al intentar enlazar los préstamos. Para solucionar esto de manera definitiva, modificamos el diseño del modelo e implementamos la generación automática de IDs nativos en los constructores principales utilizando `UUID.randomUUID().toString()`, dejando los constructores con ID manual mapeados únicamente para la reconstrucción de datos desde el motor de persistencia CSV.

