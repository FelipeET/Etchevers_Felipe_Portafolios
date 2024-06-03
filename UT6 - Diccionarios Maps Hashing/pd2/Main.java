/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD2;

/**
 *
 * @author Felipe
 */
public class Main {

    /**
     * Punto de inicio del programa.
     * @param args Ignorado.
     */
    public static void main(String[] args) throws HashTableOverloadException {
        
        String[] insertar = ManejadorArchivosGenerico.leerArchivo("src\\com\\ut6\\pd2\\claves_insertar.txt");
        String[] buscar = ManejadorArchivosGenerico.leerArchivo("src\\com\\ut6\\pd2\\claves_buscar.txt");

        System.out.println("Factor de carga\t|\tP.C insercion\t|\tP.C busqueda exitosa\t|\tP.C busqueda fallida|");

        for(int fDeCarga : new int[]{70, 75, 80, 85, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99}){

            // tabla de hash con tamaño N + N * (fDeCarga / 100)
            HashNumeros hash = new HashNumeros(insertar.length + (int)(insertar.length * ((double)fDeCarga/100)));

            System.out.print("\t\t"+fDeCarga+"%\t\t|\t");

            // Inserta todas las claves en la tabla de hash
            for(String toIns : insertar){
                hash.insertar(Integer.parseInt(toIns));
            }

            System.out.print("\t"+((double)HashNumeros.comparaciones_insercion/insertar.length)+" \t\t|");

            // Busca las claves a buscar en la tabla de hash
            for (String toSearch : buscar) {
                hash.buscar(Integer.parseInt(toSearch));
            }

            System.out.print("\t\t\t"+((double)HashNumeros.comparaciones_busqueda_exitosa/buscar.length)+" \t\t\t|");
            System.out.println("\t\t\t"+((double)HashNumeros.comparaciones_busqueda_fallida/buscar.length)+" \t\t|");


            // Reinicia los contadores
            HashNumeros.comparaciones_insercion = 0;
            HashNumeros.comparaciones_busqueda_exitosa = 0;
            HashNumeros.comparaciones_busqueda_fallida = 0;
        }

    }

}
