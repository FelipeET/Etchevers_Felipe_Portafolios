/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut7.pd5;

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;


/**
 *
 * @author felipe
 */
public class PruebaGrafo {

    public static void main(String[] args) {
        
        TGrafoDirigido grafo = (TGrafoDirigido) UtilGrafos.cargarGrafo("./UT7/PD5/aeropuertos.txt",
                "./UT7/PD5/conexiones.txt",false, TGrafoDirigido.class);

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
        
        //CONSULTAS CONECTIVIDAD
        boolean[][] conexiones = grafo.warshall();
        Scanner sn = new Scanner(System.in);
        System.out.println("Escribe el aeropuerto de partida: ");
        String partida;
        partida = sn.next();
        System.out.println("Escribe el aeropuerto de destino: ");
        String destino;
        destino = sn.next();
        HashMap<String, String> map = new HashMap<String, String>();
        String[] vertices = ManejadorArchivosGenerico.leerArchivo("./UT7/PD5/aeropuertos.txt", false);
        int i = 0;
        for(String v : vertices){
            map.put(v,i+"");
            i++;
        }
        int par = Integer.parseInt(map.get(partida));
        int des = Integer.parseInt(map.get(destino));
        System.out.println("La conexion desde "+partida+" hasta "+destino+" es :"+conexiones[par][des]);
        
        
        //BPF
        LinkedList<TVertice> res = grafo.bpf();
        System.out.println("BPF NORMAL:");
        int j = 1;
        for(TVertice v : res){
            System.out.println(j+"- "+v.getEtiqueta());
            j++;
        }
        
        LinkedList<TVertice> resM = grafo.bpf("Montevideo");
        System.out.println("BPF DESDE MONTEVIDEO:");
        int l = 1;
        for(TVertice v : resM){
            System.out.println(l+"- "+v.getEtiqueta());
            l++;
        }
        
        //TODOS LOS CAMINOS
        TCaminos todos = grafo.todosLosCaminos("Montevideo", "Buenos_Aires");
        todos.imprimirCaminosConsola();
       
       //TIENECICLO
       System.out.println("El arbol tiene ciclos: "+grafo.tieneCiclo());
       
        //ORDENACIONTOPOLOGICA
        LinkedList<TVertice> topologica = grafo.clasificacionTopologica("Montevideo");
        System.out.println("Ordenacion topologica desde Montevideo:");
        int q = 1;
        for(TVertice v : topologica){
            System.out.println(q+"- "+v.getEtiqueta());
            q++;
        }
        
        //ES CONEXO
        System.out.println("El grafo es conexo: "+grafo.esConexo(grafo.warshall()));
        
        System.out.println("El grafo es cinexo: "+grafo.esConexo());
        
    }
}
