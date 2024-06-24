/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut7.pd2;

/**
 *
 * @author felipe
 */
public class PruebaGrafo {

    public static void main(String[] args) {
        
        TGrafoDirigido grafo = (TGrafoDirigido) UtilGrafos.cargarGrafo("./UT7/PD2/vertices.txt", "./UT7/PD2/aristas.txt",false, TGrafoDirigido.class);

        Object[] etiquetasarray = grafo.getEtiquetasOrdenado();

        //MATRIZ DE ADYACENCIAS
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(grafo.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, grafo.getVertices(), "Matriz");
        
        //MATRIZ DE COSTOS MINIMOS FLOYD
        Double[][] mfloyd = grafo.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, grafo.getVertices(), "Matriz luego de FLOYD");
        
        //EXCENTRICIDAD Y CENTRO
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + grafo.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + grafo.centroDelGrafo());
       
       
    }
}
