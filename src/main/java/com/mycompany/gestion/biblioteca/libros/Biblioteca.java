package com.mycompany.gestion.biblioteca.libros;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Libro> libros = new ArrayList<>();

    // Agregar un libro
    public void agregarLibro(Libro libro) {
        libros.add(libro);

        MetodosAuxiliares.imprimirSeparador();
        System.out.println("Libro agregado correctamente.");
    }

    // Listar todos los libros con sus datos en forma tabulada.
    public void listarLibros() {
        if (libros.isEmpty()) {
            MetodosAuxiliares.advertenciaListaVacia();
            return;
        }

        System.out.println("LISTADO DE LIBROS ");
        MetodosAuxiliares.mostrarTituloDatosFormaTabulada();

        for (Libro libro : libros) {
            MetodosAuxiliares.mostrarDatosDeFormaTabulada(libro);
        }
    }

    // Buscar libros por autor (o parte del nombre del autor).
    public void buscarLibrosPorAutor(String autor) {
        if (libros.isEmpty()) {
            MetodosAuxiliares.advertenciaListaVacia();
            return;
        }

        MetodosAuxiliares.mostrarTituloDatosFormaTabulada();

        for (Libro libro : libros) {
            String autorSinTilde, inputAutorSinTilde;
            autorSinTilde = MetodosAuxiliares.quitarTildes(libro.getAutor());
            inputAutorSinTilde = MetodosAuxiliares.quitarTildes(autor);

            if (autorSinTilde.toLowerCase().contains(inputAutorSinTilde.toLowerCase())) {
                MetodosAuxiliares.mostrarDatosDeFormaTabulada(libro);
            }
        }
    }

    // Mostrar libros publicados antes de un año dado.
    public void librosPublicadosAntesDeUnAnioDado(int anioIngresado) {
        if (libros.isEmpty()) {
            MetodosAuxiliares.advertenciaListaVacia();
            return;
        }

        MetodosAuxiliares.mostrarTituloDatosFormaTabulada();

        for (Libro libro : libros) {
            if (libro.getAnioPublicacion() < anioIngresado) {
                MetodosAuxiliares.mostrarDatosDeFormaTabulada(libro);
            }
        }
    }

    // Cantidad total de ejemplares de todos los libros
    public void cantidadTotalEjemplares() {
        MetodosAuxiliares.imprimirSeparador();
        System.out.println("CANTIDAD TOTAL DE EJEMPLARES EN LA BIBLIOTECA");
        int sumaEjemplares = 0;

        for (Libro libro : libros) {
            sumaEjemplares += libro.getCantidadEjemplares();
        }

        System.out.println(sumaEjemplares + " ejemplares en total.");
    }
}
