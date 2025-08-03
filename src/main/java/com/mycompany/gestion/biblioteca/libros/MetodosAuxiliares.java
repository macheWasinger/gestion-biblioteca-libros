package com.mycompany.gestion.biblioteca.libros;

import java.text.Normalizer;
import java.time.Year;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MetodosAuxiliares {

    // MÉTODOS / FUNCIONES AUXILIARES
    public static void imprimirSeparador() {
        System.out.println("------------------------");
    }

    public static String capitalizarTitulo(String frase) {
        return frase.substring(0, 1).toUpperCase() + frase.substring(1).toLowerCase();
    }

    public static String capitalizarAutor(String frase) {
        String[] palabras = frase.trim().split("\\s+");

        StringBuilder capitalizado = new StringBuilder();

        for (String palabra : palabras) {
            if (palabra.length() > 0) {

                capitalizado.append(palabra.substring(0, 1).toUpperCase())
                        .append(palabra.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitalizado.toString().trim();
    }
    
    public static String quitarTildes(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return patron.matcher(normalizado).replaceAll("");
    }

    public static void mostrarTituloDatosFormaTabulada() {
        System.out.printf("%-30s %-25s %-10s %-10s%n", "Título", "Autor", "Año", "Ejemplares");
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void mostrarDatosDeFormaTabulada(Libro libro) {
        System.out.printf("%-30s %-25s %-10d %-10d%n", libro.getTitulo(),
                libro.getAutor(), libro.getAnioPublicacion(),
                libro.getCantidadEjemplares());
    }

    // Advertencia lista vacía
    public static void advertenciaListaVacia() {
        MetodosAuxiliares.imprimirSeparador();
        System.out.println("La lista de libros se encuentra vacía.");
    }

    // VALIDACIONES
    public static boolean palabraInvalida(String palabra) {
        return (palabra == null || palabra.isBlank());
    }

    public static int leerIntPositivo(Scanner sc, String mensaje) {
        int valor = -1;
        do {
            System.out.print(mensaje);
            String linea = sc.nextLine();
            try {
                valor = Integer.parseInt(linea);
                if (valor < 0) {
                    System.out.println("El valor no puede ser negativo. Intentá otra vez.");
                } else if (valor > Year.now().getValue()) {
                    // "Year.now().getValue()", para obtener el año actual.
                    System.out.println("El año no puede ser mayor al actual. Intentá otra vez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingresá un número entero válido.");
            }
        } while (valor < 0 || valor > Year.now().getValue());
        return valor;
    }
    
    public static int leerIntPositivoYanio(Scanner sc, String mensaje, int min, int max) {
        int numero = -1;

        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine();
            imprimirSeparador();

            try {
                numero = Integer.parseInt(entrada);

                if (numero < min || numero > max) {
                    System.out.println("Por favor ingresá un número entre " + min + " y " + max + ".");
                } else {
                    break; // número válido, salgo del bucle
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresá un número entero válido.");
            }
            imprimirSeparador();
        }
        return numero;
    }

    // Para validar que el usuario solo ingrese un string y no números enteros
    public static String solicitarEntradaNextLine(Scanner sc, String mensaje, String tipo) {
        String input;

        while (true) {
            System.out.print(mensaje);
            input = sc.nextLine().trim();

            if (input.isBlank()) {
                System.out.println("El " + tipo + " no puede estar vacío.");
            } else if (!input.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$")) {
                System.out.println("El " + tipo + " solo debe contener letras y espacios.");
            } else {
                break;
            }
        }

        return capitalizarAutor(input);
    }
}