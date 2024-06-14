package com.example.literatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LibroResponse {

    @JsonProperty("results")
    private List<Libro> results;

    public List<Libro> getResults() {
        return results;
    }
    public void setResults(List<Libro> results) {
        this.results = results;
    }
}
