/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Felipe
 */
public class Programa {

    public static void main(String[] args) {
        Map<String, Integer> indice = indizarLibro("resources/libro.txt");

        List<String> clavesOrdenadas = indice.keySet().stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return indice.get(o2).compareTo(indice.get(o1));
            }
        }).collect(Collectors.toList());

        System.out.println("TOP 10 PALABRAS POR OCURRENCIAS: ");
        for (int i = 0; i < 10; i++) {
            System.out
                    .println((i + 1) + ": " + clavesOrdenadas.get(i) + " (" + indice.get(clavesOrdenadas.get(i)) + ")");
        }

    }

    private static Map<String, Integer> indizarLibro(String pathToFile) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(pathToFile);
        Map<String, Integer> palabrasOcurrencias = new HashMap<>();

        for (String linea : lineas) {
            String[] palabras = linea.trim()
                    .replace(",", "")
                    .replace(".", "")
                    .replace("!", "")
                    .replace("?", "")
                    .replace("¿", "")
                    .replace("\"", "")
                    .split(" ");

            for (String palabra : palabras) {
                palabra = palabra.toLowerCase();
                if (!palabrasOcurrencias.containsKey(palabra)) {
                    palabrasOcurrencias.put(palabra, 0);
                }
                palabrasOcurrencias.put(palabra, palabrasOcurrencias.get(palabra) + 1);
            }
        }

        return palabrasOcurrencias;
    }

}
