/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD4;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class ManejadorArchivosGenerico {

    public static void escribirArchivo(String nombreCompletoArchivo,
            String linea) {
        escribirArchivo(nombreCompletoArchivo, new String[]{linea});
    }

    public static void escribirArchivo(String nombreCompletoArchivo,
            String[] listaLineasArchivo) {
        FileWriter fw;
        try {
            fw = new FileWriter(nombreCompletoArchivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaLineasArchivo.length; i++) {
                String lineaActual = listaLineasArchivo[i];
                bw.write(lineaActual);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "
                    + nombreCompletoArchivo);
            e.printStackTrace();
        }
    }

    public static String[] leerArchivo(String nombreCompletoArchivo) {
        FileReader fr;
        ArrayList<String> listaLineasArchivo = new ArrayList<String>();
        try {
            fr = new FileReader(nombreCompletoArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                listaLineasArchivo.add(lineaActual);
                lineaActual = br.readLine();
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo "
                    + nombreCompletoArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo "
                    + nombreCompletoArchivo);
            e.printStackTrace();
        }
        System.out.println("Archivo leido satisfactoriamente");

        return listaLineasArchivo.toArray(new String[0]);
    }

}
