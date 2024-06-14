package com.example.literatura;

import com.example.literatura.model.AutorEntity;
import com.example.literatura.model.Libro;
import com.example.literatura.model.LibroEntity;
import com.example.literatura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class LiterAturaApplication implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;

    public static void main(String[] args) {
        SpringApplication.run(LiterAturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        mostrarElMenu(scan);
    }

    public void mostrarElMenu(Scanner scan) {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    *************************************************
                    Opciones:
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    *************************************************
                    """;

            System.out.println(menu);
            System.out.print("Ingrese la opcion: ");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibros(scan);
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosDesdeHasta(scan);
                    break;
                case 5:
                    menuIdiomas(scan);
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    scan.close();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        scan.close();
        System.exit(0);
    }

    private void buscarLibros(Scanner scan) {
        System.out.print("Ingrese nombre del libro a buscar: ");
        String query = scan.nextLine().toLowerCase();
        var libros = gutendexService.buscarLibros(query);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con el título de búsqueda proporcionado.");
            return;
        }

        System.out.println("************************************************");
        System.out.println("Libros encontrados:");
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            String autorNombre = libro.getAuthors().isEmpty() ? "Autor desconocido" : libro.getAuthors().get(0).getName();
            System.out.println("ID: " + libro.getId() + " - Título: " + libro.getTitle() + " - Autor: " + autorNombre);
        }
        System.out.println("************************************************");
        System.out.print("¿Desea guardar alguno de estos libros en la colección? (si/no): ");
        String respuesta = scan.nextLine();

        if (respuesta.equalsIgnoreCase("si")) {
            System.out.print("Ingrese el ID del libro que desea guardar: ");
            int idLibro = scan.nextInt();
            scan.nextLine(); // consume newline

            Libro libroSeleccionado = libros.stream()
                    .filter(libro -> libro.getId() == idLibro)
                    .findFirst()
                    .orElse(null);

            if (libroSeleccionado != null) {
                gutendexService.guardarLibro(libroSeleccionado);
                System.out.println("Libro guardado: " + libroSeleccionado.getTitle());
            } else {
                System.out.println("ID de libro inválido.");
            }
        }
    }

    private void listarLibrosRegistrados(){
        List<LibroEntity> librosRegistrados = gutendexService.listarLibrosRegistrados();

        if (librosRegistrados.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        System.out.println("************************************************");
        System.out.println("Libros registrados:");
        for (LibroEntity libro : librosRegistrados) {
            Collectors Collectors = null;
            System.out.println("ID: " + libro.getId() + " - Título: " + libro.getTitle() + " - Autor(es): " + libro.getAuthors().stream()
                    .map(AutorEntity::getName)
                    .collect(Collectors.joining(", ")));
        }
    }

    private void listarAutoresRegistrados() {
        List<AutorEntity> autoresRegistrados = gutendexService.listarAutoresRegistrados();
        System.out.println("Autores registrados:");
        for(AutorEntity autor : autoresRegistrados){
            String libro = autor.getLibro() != null ? autor.getLibro().getTitle() : "Ningún libro registrado"; //causa error
            System.out.println("Autor/es: "+autor.getName()+ ", Año de nacimiento:  "+ autor.getBirthYear()+ ", Año de fallecimiento: "+ autor.getDeathYear()+ ", Libros del autor: "+ libro);
        }
    }


    private void menuIdiomas(Scanner scan){
        int opcionIdioma;
        do{
        System.out.print("Ingrese [1] para inglés, [2] para español: ");
         opcionIdioma = scan.nextInt();
        scan.nextLine().toLowerCase(); // Consumir la nueva línea pendiente después de nextInt()
            switch (opcionIdioma) {
                case 1:
                    listarLibrosPorIdioma("en"); // Llamar al método con "en" para inglés
                    break;
                case 2:
                    listarLibrosPorIdioma("es"); // Llamar al método con "es" para español
                    break;
                default:
                    System.out.println("Ingrese una opción válida.");
            }
        } while (opcionIdioma != 1 && opcionIdioma != 2);
    }

    private void listarLibrosPorIdioma(String idioma) {
        List<LibroEntity> librosPorIdioma = gutendexService.listarLibrosPorIdioma(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros en el idioma especificado.");
            return;
        }

        System.out.println("****************************");
        System.out.println("[en] Inglés, [es] Español");
        System.out.println("Idioma [" + idioma + "]:");
        for (LibroEntity libro : librosPorIdioma) {
            System.out.println("ID: " + libro.getId() + " - Título: " + libro.getTitle() + " - Autor(es): " + libro.getAuthors().stream()
                    .map(AutorEntity::getName)
                    .collect(Collectors.joining(", ")));
        }
    }

    private void listarAutoresVivosDesdeHasta(Scanner scan) {
        System.out.print("Ingrese el año:");
        while (!scan.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un año válido.");
            scan.next(); // consume el input inválido
        }
        int year = scan.nextInt();
        scan.nextLine(); // consume newline

        List<AutorEntity> autoresVivos = gutendexService.listarAutoresVivosEnAno(year);
        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año especificado.");
            return;
        }

        System.out.println("****************************");
        System.out.println("Autores vivos en el año " + year + ":");
        for (AutorEntity autor : autoresVivos) {
            System.out.println("Nombre: " + autor.getName() + " - Año de nacimiento: " + autor.getBirthYear() + " - Año de fallecimiento: " + autor.getDeathYear());
        }
    }

}
