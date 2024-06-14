package com.example.literatura.controller;

import com.example.literatura.model.Libro;
import com.example.literatura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class LibroController {

    @Autowired
    private GutendexService gutendexService;

    @GetMapping("/libro")
    public List<Libro> buscarLibro(@RequestParam String query) {
        return gutendexService.buscarLibros(query);
    }

    @GetMapping("/libro/autor")
    public List<Libro> buscarLibrosPorAutor(@RequestParam String autor) {
        return gutendexService.buscarLibrosPorAutor(autor);
    }

}
