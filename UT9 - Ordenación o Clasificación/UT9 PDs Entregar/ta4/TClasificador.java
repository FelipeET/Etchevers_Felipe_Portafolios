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
public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;

    /**
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion, boolean cascara) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                if (cascara) {
                    return ordenarPorInsercionCascara(datosParaClasificar);
                } else {
                    return ordenarPorInsercion(datosParaClasificar);
                }
            case METODO_CLASIFICACION_SHELL:
                if (cascara) {
                    return ordenarPorShellCascara(datosParaClasificar);
                } else {
                    return ordenarPorShell(datosParaClasificar);
                }
            case METODO_CLASIFICACION_BURBUJA:
                if (cascara) {
                    return ordenarPorBurbujaCascara(datosParaClasificar);
                } else {
                    return ordenarPorBurbuja(datosParaClasificar);
                }
            case METODO_CLASIFICACION_QUICKSORT:
                if (cascara) {
                    return ordenarPorQuickSortCascara(datosParaClasificar);
                } else {
                    return ordenarPorQuickSort(datosParaClasificar);
                }
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j -= inc;
                    }
                }
            }
        }
        return datosParaClasificar;
    }
    
    protected int[] ordenarPorShellCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
                    intercambiar(datosParaClasificar, j + 1, j);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }
    
    protected int[] ordenarPorInsercionCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }
    
    protected int[] ordenarPorBurbujaCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }
    
    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        int altura = quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }
    
    private int quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivoteElementoCentral(izquierda, derecha);
        if (posicionPivote >= 0) {
            // El pivote es el elemento de la entrada cuyo índice es "posicionPivote".
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    // L debe moverse a la derecha.
                    izquierda++;
                }

                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    // R debe moverse a la izquierda.
                    derecha--;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            int maxAltura = 0;

            // Un tercer error relacionado con las llamadas recursivas,
            // respecto a los límites de los subarreglos.
            if (i < derecha) {
                // derecha es el fin del subvector izquierdo.
                int alturaIzquierda = quicksort(entrada, i, derecha);
                if (maxAltura < alturaIzquierda) {
                    maxAltura = alturaIzquierda;
                }
            }
            if (izquierda < j) {
                // izquierda es el fin del subvector derecho.
                int alturaDerecha = quicksort(entrada, izquierda, j);
                if (maxAltura < alturaDerecha) {
                    maxAltura = alturaDerecha;
                }
            }

            return maxAltura + 1;
        }
        return 0;
    }
    
    protected int[] ordenarPorQuickSortCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    private int encuentraPivoteElementoCentral(int i, int j) {
        return (int) ((j - i - 1) /2)+i;
    }
    
    private int encuentraPivoteRandom(int i, int j, int[] entrada, int seed) {
        Random ran = new Random();
        ran.setSeed(seed);
        return ran.nextInt(i, j);
    }
    
    public boolean estaOrdenado(int[] datosClasificados, boolean ordenDescendente) {
        int i = 0;
        if (ordenDescendente) {
            while (i < datosClasificados.length - 1){
                if (datosClasificados[i] > datosClasificados[i + 1]) {
                    i++;
                }
                else return false;
            }
        } else {
            while (i < datosClasificados.length - 1) {
                if (datosClasificados[i] < datosClasificados[i + 1]) {
                    i++;
                }
                else return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        //int[] vectorAleatorio = gdg.generarDatosAleatorios();
        //int[] vectorAscendente = gdg.generarDatosAscendentes();
        //int[] vectorDescendente = gdg.generarDatosDescendentes();

        int[] vectorAleatorio1 = gdg.generarDatosAleatorios();
        int[] resInsercion = clasif.clasificar(vectorAleatorio1,
                METODO_CLASIFICACION_INSERCION, false);
        for (int i = 0; i < resInsercion.length; i++) {
            System.out.print(resInsercion[i] + " ");
        }
        System.out.println("\nEl vector esta ordenado con insercion = " + clasif.estaOrdenado(resInsercion,false));
        
        int[] vectorAleatorio2 = gdg.generarDatosAleatorios();
        int[] resShell = clasif.clasificar(vectorAleatorio2,
                METODO_CLASIFICACION_SHELL, false);
        for (int i = 0; i < resShell.length; i++) {
            System.out.print(resShell[i] + " ");
        }
        System.out.println("\nEl vector esta ordenado con shellsort = " + clasif.estaOrdenado(resShell,false));
        
        int[] vectorAleatorio3 = gdg.generarDatosAleatorios();
        int[] resBurbuja = clasif.clasificar(vectorAleatorio3,
                METODO_CLASIFICACION_BURBUJA, false);
        for (int i = 0; i < resBurbuja.length; i++) {
            System.out.print(resBurbuja[i] + " ");
        }
        System.out.println("\nEl vector esta ordenado con burbuja = " + clasif.estaOrdenado(resBurbuja,false));
        
        int[] vectorAleatorio4 = gdg.generarDatosAleatorios();
        int[] resQuicksort = clasif.clasificar(vectorAleatorio4,
                METODO_CLASIFICACION_QUICKSORT, false);
        for (int i = 0; i < resQuicksort.length; i++) {
            System.out.print(resQuicksort[i] + " ");
        }
        System.out.println("\nEl vector esta ordenado con quicksort = " + clasif.estaOrdenado(resQuicksort,false));
        
    }

}
