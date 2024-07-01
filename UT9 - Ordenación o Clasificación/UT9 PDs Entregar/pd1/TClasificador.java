/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut9.pd1;

/**
 *
 * @author felipe
 */
public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;

    /**
     * Punto de entrada al clasificador
     *
     * @param metodoClasificacion
     * @param orden
     * @param tamanioVector
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELL:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_BURBUJA:
                return ordenarPorBurbuja(datosParaClasificar);
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
                METODO_CLASIFICACION_INSERCION);
        for (int i = 0; i < resInsercion.length; i++) {
            System.out.print(resInsercion[i] + " ");
        }
        System.out.println("\nEl vector corto esta ordenado = " + clasif.estaOrdenado(resInsercion,false));
        
        int[] vectorAleatorio2 = gdg.generarDatosAleatorios();
        int[] resShell = clasif.clasificar(vectorAleatorio2,
                METODO_CLASIFICACION_SHELL);
        for (int i = 0; i < resShell.length; i++) {
            System.out.print(resShell[i] + " ");
        }
        System.out.println("\nEl vector corto esta ordenado = " + clasif.estaOrdenado(resShell,false));
        
        int[] vectorAleatorio3 = gdg.generarDatosAleatorios();
        int[] resBurbuja = clasif.clasificar(vectorAleatorio3,
                METODO_CLASIFICACION_BURBUJA);
        for (int i = 0; i < resBurbuja.length; i++) {
            System.out.print(resBurbuja[i] + " ");
        }
        System.out.println("\nEl vector corto esta ordenado = " + clasif.estaOrdenado(resBurbuja,false));        
    }
}
