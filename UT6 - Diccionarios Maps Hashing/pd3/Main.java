/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Felipe
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        int ran = random.nextInt(200 - 0 + 1) + 0;

        //EJERCICIO1
        System.out.println("EJERCICIO 1");

        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i <= ran; i++) {
            if (random.nextBoolean()) {
                map.put(String.valueOf(i), null);
            } else {
                map.put(String.valueOf(i), String.valueOf(i));
            }
        }

        ej1(map);

        
        //EJERCICO2
        System.out.println("EJERCICIO 2");

        HashMap<String, String> map2 = new HashMap<>();
        for (int i = 0; i <= ran; i++) {
            map2.put(String.valueOf(i), String.valueOf(i + 1));
        }

        ej2(map2);

        
        //EJERCICIO3
        System.out.println("EJERCICIO 3");

        Scanner sn = new Scanner(System.in);
        System.out.println("Escribe las palabras a imprimir cadena o (\"-Q\" para terminar): ");
        String last;
        ArrayList<String> strings = new ArrayList<>();
        while (true) {
            last = sn.next();
            if (last.equalsIgnoreCase("-q")) {
                break;
            }
            strings.add(last);
        }

        ej3(strings);

        
        //EJERCICIO4
        System.out.println("EJERCICIO 4");
        
        ej4();
    }

    private static void ej1(HashMap<String, String> map) {

        System.out.println("Null before cleaning: " + map.containsValue(null));

        map.entrySet().stream().filter((kv) -> kv.getValue() == null).map(Map.Entry::getKey).collect(Collectors.toList()).forEach(map::remove);

        System.out.println("Null after cleaning: " + map.containsValue(null));
    }

    private static void ej2(HashMap<String, String> map) {

        System.out.println("Mapa antes de hacer flip: ");
        for (String key : map.keySet()) {
            System.out.print(" - " + key + ": " + map.get(key) + "  |  ");
        }
        try {
            map = (HashMap<String, String>) flipMap(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\nMapa despues de hacer flip: ");
        for (String key : map.keySet()) {
            System.out.print(" - " + key + " : " + map.get(key) + "  |  ");
        }

    }

    private static Map<String, String> flipMap(Map<String, String> original) {
        Map<String, String> result = new HashMap<>();

        for (Map.Entry<String, String> entrySet : original.entrySet()) {
            if (entrySet.getValue() == null) {
                throw new NullPointerException("El mapa contiene valores NULL");
            }
            if (result.containsKey(entrySet.getValue())) {
                throw new IllegalArgumentException("El mapa contiene valores duplicados");
            }
            result.put(entrySet.getValue(), entrySet.getKey());
        }

        return result;
    }

    private static void ej3(ArrayList<String> strings) {

        strings.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    //lexicografico
                    return o1.compareTo(o2);
                }
                return o1.length() > o2.length() ? 1 : -1;
            }
        });

        for (String string : strings) {
            System.out.println(string);
        }

    }

    private static void ej4() throws IOException {
        // Asumo que no se puede utilizar ManejadorDeArchivosGenerico
        Scanner scanner = new Scanner(System.in);

        // Lee nombre de archivo
        System.out.println("Ingrese nombre del archivo de palabras:");
        String fileName = scanner.nextLine();

        // Verifica que el archivo exista
        File file = new File("resources/" + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("No se encontro el archivo con el nombre \"" + file.getName() + "\"");
        }

        // Lee cantidad de palabras a imprimir
        System.out.println("Ingrese la cantidad de lineas a imprimir:");
        int lineasAImprimir = scanner.nextInt();
        scanner.close();

        // Asumo que el largo promedio de las palabras es 5
        int cantPalabras = (int) Math.ceil(file.length() / 5.0);

        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);

        // Cargar lineas a array de lineas
        String line;
        String[] lines = new String[cantPalabras];
        int index = 0;
        while ((line = br.readLine()) != null) {
            if (index >= cantPalabras) {
                System.out.println("LA CANTIDAD DE PALABRAS ESPERADA ERA " + cantPalabras + " PERO LA CANTIDAD REAL ES MAYOR");
                System.out.println("Ignorando palabras a partir de la linea " + (index + 1));
                break;
            }
            lines[index++] = line;
        }

        // Imprime
        Random random = new Random();
        for (int i = 0; i < lineasAImprimir; i++) {
            String selected;
            while ((selected = lines[random.nextInt(lines.length)]) == null) {
            }
            System.out.println(selected);
        }

        fileReader.close();
        br.close();
    }

}
