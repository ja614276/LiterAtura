package com.example.literatura.service;
import com.example.literatura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LibroService {
    @Autowired
    private LibroRepository repository;

//    public List<LibroDTO> obtenerTodosLosLibros() {
//        return convierteDatos(repository.findAll());
//    }
//
//    public List<LibroDTO> convierteDatos(List<Libro> libros) {
//        return libros.stream()
//                .map(l -> new LibroDTO(
//                        l.getId(),
//                        l.getTitulo(),
//                        l.getAutor(),
//                        l.getIdioma(),
//                        l.getNumeroDeDescargas()))
//                .collect(Collectors.toList());
//    }


}
