package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    @Autowired
    private LibroService libroService;

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n========================================");
            System.out.println("     CATÁLOGO DE LIBROS - GUTENDEX");
            System.out.println("========================================");
            System.out.println("1 - Buscar libro por título");
            System.out.println("2 - Listar libros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos en un determinado año");
            System.out.println("5 - Listar libros por idioma");
            System.out.println("0 - Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> buscarLibroPorTitulo();
                    case 2 -> listarLibrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivos();
                    case 5 -> listarLibrosPorIdioma();
                    case 0 -> System.out.println("\n¡Gracias por usar el catálogo! Hasta pronto.");
                    default -> System.out.println("\nOpción inválida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, ingrese un número válido.");
            }
        }

        scanner.close();
    }

    private void buscarLibroPorTitulo() {
        System.out.print("\nIngrese el título del libro: ");
        String titulo = scanner.nextLine();

        System.out.println("\nBuscando libro...");
        Libro libro = libroService.buscarYGuardarLibro(titulo);

        if (libro != null) {
            System.out.println("\n✓ Libro guardado exitosamente:");
            System.out.println(libro);
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroService.listarLibrosRegistrados();

        if (libros.isEmpty()) {
            System.out.println("\nNo hay libros registrados en la base de datos.");
            return;
        }

        System.out.println("\n========== LIBROS REGISTRADOS ==========");
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = libroService.listarAutoresRegistrados();

        if (autores.isEmpty()) {
            System.out.println("\nNo hay autores registrados en la base de datos.");
            return;
        }

        System.out.println("\n========== AUTORES REGISTRADOS ==========");
        autores.forEach(autor -> {
            System.out.println(autor);
            System.out.println("Libros: " + autor.getLibros().stream()
                    .map(Libro::getTitulo)
                    .toList());
            System.out.println("----------------------------------");
        });
    }

    private void listarAutoresVivos() {
        System.out.print("\nIngrese el año: ");
        try {
            Integer ano = Integer.parseInt(scanner.nextLine());
            List<Autor> autores = libroService.listarAutoresVivosEnAno(ano);

            if (autores.isEmpty()) {
                System.out.println("\nNo hay autores vivos en el año " + ano + " en la base de datos.");
                return;
            }

            System.out.println("\n===== AUTORES VIVOS EN " + ano + " =====");
            autores.forEach(autor -> {
                System.out.println(autor);
                System.out.println("----------------------------------");
            });
        } catch (NumberFormatException e) {
            System.out.println("\nAño inválido.");
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("\nIdiomas disponibles:");
        System.out.println("es - Español");
        System.out.println("en - Inglés");
        System.out.println("fr - Francés");
        System.out.println("pt - Portugués");
        System.out.print("\nIngrese el código del idioma: ");

        String idioma = scanner.nextLine().toLowerCase();
        List<Libro> libros = libroService.listarLibrosPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("\nNo hay libros registrados en ese idioma.");
            return;
        }

        System.out.println("\n===== LIBROS EN " + idioma.toUpperCase() + " =====");
        libros.forEach(System.out::println);
    }
}