package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dto.LibroDTO;
import com.aluracursos.literalura.dto.RespuestaAPI;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private static final String URL_BASE = "https://gutendex.com/books/?search=";

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ConvierteDatos convierteDatos;

    @Transactional
    public Libro buscarYGuardarLibro(String titulo) {
        // Verificar si el libro ya existe
        Optional<Libro> libroExistente = libroRepository.findByTituloIgnoreCase(titulo);
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya está registrado en la base de datos.");
            return libroExistente.get();
        }

        // Buscar en la API
        String json = consumoAPI.obtenerDatos(URL_BASE + titulo.replace(" ", "%20"));
        RespuestaAPI respuesta = convierteDatos.obtenerDatos(json, RespuestaAPI.class);

        if (respuesta.resultados().isEmpty()) {
            System.out.println("No se encontró el libro en la API.");
            return null;
        }

        LibroDTO datosLibro = respuesta.resultados().get(0);

        // Crear o buscar el autor
        Autor autor = null;
        if (!datosLibro.autores().isEmpty()) {
            var datosAutor = datosLibro.autores().get(0);
            Optional<Autor> autorExistente = autorRepository.findByNombre(datosAutor.nombre());

            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                autor = new Autor(
                        datosAutor.nombre(),
                        datosAutor.anoNacimiento(),
                        datosAutor.anoFallecimiento()
                );
                autor = autorRepository.save(autor);
            }
        }

        // Crear y guardar el libro
        Libro libro = new Libro(
                datosLibro.titulo(),
                datosLibro.idioma().isEmpty() ? "desconocido" : datosLibro.idioma().get(0),
                datosLibro.numeroDescargas()
        );
        libro.setAutor(autor);

        return libroRepository.save(libro);
    }

    public List<Libro> listarLibrosRegistrados() {
        return libroRepository.findAll();
    }

    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEnAno(Integer ano) {
        return autorRepository.findAutoresVivosEnAno(ano);
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}