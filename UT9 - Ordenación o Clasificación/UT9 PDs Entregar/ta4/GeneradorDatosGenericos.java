/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut9.ta4;

import java.util.Random;

/**
 *
 * @author felipe
 */
public class GeneradorDatosGenericos {

    private static int TAMANIO_MAX = 32000;
    
    protected int[] generarDatos(int t, String tipoOrden){
        int[] datos = new int[0];
        TAMANIO_MAX = t;
        switch(tipoOrden){
            case "ascendente":
                return generarDatosAscendentes();
            case "descendente":
                return generarDatosDescendentes();
            case "aleatorio":
                return generarDatosAleatorios();
            default:
                System.out.println("Este codigo no deberia haberse ejecutado al generar los datos");
            break;
        }
        return datos;
    }

    public int[] generarDatosAleatorios() {
        Random rnd = new Random();
        int[] datosGenerados = new int[TAMANIO_MAX];
        boolean[] datosUtilizados = new boolean[TAMANIO_MAX];
        for (int i = 0; i < datosGenerados.length; i++) {
            int j = rnd.nextInt(TAMANIO_MAX);
            while (datosUtilizados[j]) {
                j = (j + 1) % TAMANIO_MAX;
            }
            datosGenerados[j] = i;
            datosUtilizados[j] = true;
        }
        return datosGenerados;
    }

    public int[] generarDatosAscendentes() {
        int[] copiaAscendente = new int[TAMANIO_MAX];
        for (int i = 0; i < TAMANIO_MAX; i++) {
            copiaAscendente[i] = i;
        }
        return copiaAscendente;
    }

    public int[] generarDatosDescendentes() {
        int[] copiaDescendente = new int[TAMANIO_MAX];
        for (int i = 0; i < TAMANIO_MAX; i++) {
            copiaDescendente[i] = TAMANIO_MAX - i;
        }
        return copiaDescendente;
    }

}
