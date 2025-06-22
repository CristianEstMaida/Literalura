package com.alura.literalura.principal;

import com.alura.literalura.DTO.Datos;
import com.alura.literalura.DTO.DatosAutor;
import com.alura.literalura.DTO.DatosLibros;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    @Autowired
    private final LibroRepository libroRepository;
    @Autowired
    private final AutorRepository autorRepository;
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private String json;
    private List<Autor> autores;
    private List<Libro> libros;

    private String menu = """
            ------------
            Elija la opción a través de su número:
            1 - buscar libro por título
            2 - listar libros registrados
            3 - listar autores registrados
            4 - listar autores vivos en determinado año
            5 - listar libros por idioma
            0 - salir
            """;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu(){
        var opcionElegida = -1;
        while (opcionElegida != 0) {
            json = consumoAPI.obtenerDatos(URL_BASE);
            System.out.println(menu);
            opcionElegida = teclado.nextInt();
            teclado.nextLine();
            switch (opcionElegida) {
                case 5 -> buscarLibrosPorIdioma();
                case 4 -> buscarAutoresVivos();
                case 3 -> mostrarAutoresBuscados();
                case 2 -> mostrarLibrosBuscados();
                case 1 -> buscarLibroPorTitulo();
                case 0 -> System.out.println("Hasta luego...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    public void buscarLibroPorTitulo() {
        DatosLibros datosLibro = recibirDatosDelLibro();
        if (datosLibro != null) {
            Libro libro;
            DatosAutor datosAutor = datosLibro.autor().get(0);
            Autor autorExistente = autorRepository.findByNombre(datosAutor.nombre());
            if(autorExistente != null){
                libro = new Libro(datosLibro, autorExistente);
            }else{
                Autor nuevoAutor = new Autor(datosAutor);
                libro = new Libro(datosLibro, nuevoAutor);
                autorRepository.save(nuevoAutor);
            }
            try {
                libroRepository.save(libro);
                System.out.println(libro);
            } catch (Exception e) {
                System.out.println("No puedes registrar un mismo libro más de una vez");
            }
        }else{
            System.out.println("No hemos encontrado el libro en la API =(");
        }
    }

    private DatosLibros recibirDatosDelLibro() {
        System.out.println("Ingrese el título del libro");
        var nombreLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE +
                "?search=" +
                nombreLibro.replace(" ", "+"));
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            return libroBuscado.get();
        }else {
            return null;
        }
    }

    private void mostrarLibrosBuscados() {
        libros = libroRepository.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getIdioma))
                .forEach(System.out::println);
    }

    private void mostrarAutoresBuscados() {
        autores = autorRepository.findAll();

        autores.stream()
                .sorted(Comparator.comparing(Autor::getFechaDeFallecimiento))
                .forEach(System.out::println);
    }

    private void buscarLibrosPorIdioma(){
        System.out.println("Ingrese el idioma para buscar los libros:\nes- español\nen- inglés\nfr- francés\npt- portugués");
        var idioma = teclado.nextLine();
        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);
        librosPorIdioma.forEach(System.out::println);
    }

    private void buscarAutoresVivos(){
        System.out.println("Ingrese el año vivo de auto(es) que desea buscar");
        int anio = Integer.valueOf(teclado.nextLine());
        List<Autor> autoresVivos = autorRepository
                .findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(anio, anio);

        autoresVivos.forEach(System.out::println);

    }
}
