package com.example.literatura.service;

import com.example.literatura.model.AutorEntity;
import com.example.literatura.model.Libro;
import com.example.literatura.model.LibroEntity;
import com.example.literatura.model.LibroResponse;
import com.example.literatura.repositorio.AutorRepository;
import com.example.literatura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public GutendexService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.restTemplate = new RestTemplate();
        this.libroRepository = libroRepository;
    }

    public List<Libro> buscarLibros(String query) {
        String url = UriComponentsBuilder.fromHttpUrl("https://gutendex.com/books")
                .queryParam("search", query)
                .toUriString();

        LibroResponse response = restTemplate.getForObject(url, LibroResponse.class);
        return response != null ? response.getResults() : List.of();
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        String url = UriComponentsBuilder.fromHttpUrl("https://gutendex.com/books")
                .queryParam("/?search=", autor)
                .toUriString();

        LibroResponse response = restTemplate.getForObject(url, LibroResponse.class);
        if (response != null && response.getResults() != null) {
            return response.getResults().stream()
                    .filter(book -> book.getAuthors().stream()
                            .anyMatch(a -> a.getName().toLowerCase().contains(autor)))
                    .collect(Collectors.toList());
        }
        return List.of();
    }


    public LibroEntity guardarLibro(Libro libro) {
        LibroEntity libroEntity = new LibroEntity();
        libroEntity.setId(libro.getId());
        libroEntity.setTitle(libro.getTitle());
        libroEntity.setDownloadCount(libro.getDownloadCount());
        libroEntity.setLanguages(libro.getLanguages());

        List<AutorEntity> authorEntities = libro.getAuthors().stream()
                .map(author -> {
                    AutorEntity autorEntity = new AutorEntity();
                    autorEntity.setName(author.getName());
                    autorEntity.setBirthYear(author.getBirth_year());
                    autorEntity.setDeathYear(author.getDeath_year());
                    autorEntity.setLibro(libroEntity);
                    return autorEntity;
                }).collect(Collectors.toList());

        libroEntity.setAuthors(authorEntities);
        libroEntity.setHtmlFormat(libro.getFormats().get("text/html"));

        return libroRepository.save(libroEntity);
    }

    @Transactional(readOnly = true)
    public List<LibroEntity> listarLibrosRegistrados(){
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AutorEntity> listarAutoresRegistrados(){
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AutorEntity> listarAutoresVivosEnAno(int year) {
        return autorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }

    @Transactional(readOnly = true)
    public List<LibroEntity> listarLibrosPorIdioma(String idioma){
        return libroRepository.findAll().stream()
                .filter(l->l.getLanguages().contains(idioma))
                .collect(Collectors.toList());
    }
}
