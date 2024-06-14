package com.example.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
//@Table(name = "libros")
public class LibroEntity {
    @Id
    private int id;
    private String title;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AutorEntity> authors;
    @ElementCollection
    private List<String> languages;
    private String htmlFormat;
    private int downloadCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AutorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AutorEntity> authors) {
        this.authors = authors;
    }

    public String getHtmlFormat() {
        return htmlFormat;
    }

    public void setHtmlFormat(String htmlFormat) {
        this.htmlFormat = htmlFormat;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}