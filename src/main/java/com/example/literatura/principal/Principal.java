package com.example.literatura.principal;

//import com.example.literatura.model.Libro;
//import com.example.literatura.repositorio.LibroRepository;
//import com.example.literatura.service.ConsumoAPI;
//import com.example.literatura.service.ConvierteDatos;
//
//import java.util.Scanner;

public class Principal {
//    private Scanner scan = new Scanner(System.in);
//    private ConsumoAPI consumoApi = new ConsumoAPI();
//    private final String URL_BASE = "https://gutendex.com/books/";
//    private final String URL_TEST = "https://gutendex.com/books/?page=2";
//    private ConvierteDatos conversor = new ConvierteDatos();
//    private LibroRepository repositorio;
//
//    public Principal(LibroRepository repository) {
//        this.repositorio = repository;
//    }
//
//
//    private void buscarLibros(Scanner scan) {
//        System.out.println("****************************");
//        System.out.println("Ingrese el libro a buscar:");
//        String query = scan.nextLine().toLowerCase();
//        var libros = gutendexService.buscarLibros(query);
//
//        if (libros.isEmpty()) {
//            System.out.println("No se encontraron libros con el título de búsqueda proporcionado.");
//            return;
//        }
//
//        System.out.println("Libros encontrados:");
//        for (int i = 0; i < libros.size(); i++) {
//            Libro libro = libros.get(i);
//            System.out.println("ID: " + libro.getId() + " - Título: " + libro.getTitle() + " - Autor: " + libro.getAuthors().get(0).getName());
//        }
//
//        System.out.println("¿Desea guardar alguno de estos libros en la colección? (si/no)");
//        String respuesta = scan.nextLine();
//
//        if (respuesta.equalsIgnoreCase("si")) {
//            System.out.println("Ingrese el ID del libro que desea guardar:");
//            int idLibro = scan.nextInt();
//            scan.nextLine(); // consume newline
//
//            Libro libroSeleccionado = libros.stream()
//                    .filter(libro -> libro.getId() == idLibro)
//                    .findFirst()
//                    .orElse(null);
//
//            if (libroSeleccionado != null) {
//                gutendexService.guardarLibro(libroSeleccionado);
//                System.out.println("Libro guardado: " + libroSeleccionado.getTitle());
//            } else {
//                System.out.println("ID de libro inválido.");
//            }
//        }
//    }
//
//
//
//    private void listarLibrosRegistrados() {
//        System.out.println("Listado de libros registrados:");
//        repositorio.findAll().forEach(System.out::println);
//    }
//
//    private void listarLibrosPorIdioma() {
//    }
//
//    private void listarAutoresVivosDesdeHasta() {
//    }
//
//    private void listarAutoresRegistrados() {
//    }


}
