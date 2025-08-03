package com.mycompany.gestion.biblioteca.libros;

import java.time.Year;
import java.util.Scanner;


public class GestionBibliotecaLibros {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        MetodosAuxiliares metodosAuxiliares = new MetodosAuxiliares();
        
        int opcion;
        boolean salir = false;
        
        while(!salir) {
            metodosAuxiliares.imprimirSeparador();
            System.out.println("Gestión de una Biblioteca de Libros");
            System.out.println("1. Agregar un libro");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Buscar libros por autor (o parte del nombre)");
            System.out.println("4. Mostrar libros publicados antes de un año dado");
            System.out.println("5. Mostrar la cantidad total de ejemplares disponibles en la biblioteca");
            System.out.println("6. Salir");
            metodosAuxiliares.imprimirSeparador();
            System.out.println("Elija una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine(); // Limpio buffer
            
            switch(opcion) {
                case 1 -> {
                    System.out.println("AGREGAR UN LIBRO");
                    
                    //----- TÍTULO -----//
                    String titulo = metodosAuxiliares.solicitarEntradaNextLine(entrada, "Ingrese un título: ", "título");
                    String tituloCapitalizado = metodosAuxiliares.capitalizarTitulo(titulo);
                    
                    
                    //----- AUTOR -----//
                    String autor = metodosAuxiliares.solicitarEntradaNextLine(entrada, "Ingrese un autor: ", "nombre del autor");
                    String autorCapitalizado = metodosAuxiliares.capitalizarAutor(autor);
                    
                    
                    //----- AÑO DE PUBLICACIÓN -----//
                    int anio = metodosAuxiliares.leerIntPositivoYanio(entrada, "Ingrese un año de publicación (no puede ser futuro): ", 1900, Year.now().getValue());
                    
                    
                    //----- CANTIDAD DE EJEMPLARES -----//
                    int cantEjemplares = metodosAuxiliares.leerIntPositivo(entrada, "Ingrese la cantidad de ejemplares: ");
                    
                    if (cantEjemplares < 0) {
                        System.out.println("La cantidad de ejemplares no puede ser un número negativo. Vuelva a ingresar.");
                        return;
                    }
                    
                    Libro libro = new Libro(tituloCapitalizado, autorCapitalizado, anio, cantEjemplares);
                    
                    biblioteca.agregarLibro(libro);
                }
                case 2 -> biblioteca.listarLibros();
                case 3 -> {
                    MetodosAuxiliares.imprimirSeparador();
                    System.out.println("BUSCAR LIBROS POR AUTOR (O PARTE DEL NOMBRE DEL AUTOR) ");
                    String autorBuscado = metodosAuxiliares.solicitarEntradaNextLine(entrada, "Ingrese el autor que desea buscar: ", "nombre del autor");
                    String autorCapitalizado = metodosAuxiliares.capitalizarAutor(autorBuscado);
                    
                    if (metodosAuxiliares.palabraInvalida(autorCapitalizado)) {
                        System.out.println("El nombre del autor es inválido. Vuelva a ingresar.");
                        return;
                    }
                    
                    biblioteca.buscarLibrosPorAutor(autorCapitalizado);
                }
                case 4 -> {
                    MetodosAuxiliares.imprimirSeparador();
                    System.out.println("MOSTRAR LIBROS PUBLICADOS ANTES DE UN AÑO DADO");
                    int anioIngresado = metodosAuxiliares.leerIntPositivoYanio(entrada, "Ingrese un año: ", 1900, Year.now().getValue());

                    biblioteca.librosPublicadosAntesDeUnAnioDado(anioIngresado);
                }
                case 5 -> biblioteca.cantidadTotalEjemplares();
                case 6 -> salir = true;
                default -> {
                    System.out.println("Opción inválida.");
                    metodosAuxiliares.imprimirSeparador();
                }
            }
        }
    }
}
