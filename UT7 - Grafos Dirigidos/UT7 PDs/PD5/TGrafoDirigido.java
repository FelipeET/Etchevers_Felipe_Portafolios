/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut7.pd5;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author felipe
 */
public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    
    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                //getLasAristas().add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Comparable menorExcentricidad = null;
        for(Comparable v: vertices.keySet()){
            Comparable excentricidadDeV = this.obtenerExcentricidad(v);
            if((menorExcentricidad == null || excentricidadDeV.compareTo(menorExcentricidad)< 0)&& !(excentricidadDeV.compareTo(0.0)==0)){
                menorExcentricidad = excentricidadDeV;
            }
        }
        return menorExcentricidad;
    }
    
    private int obtenerPosicionVertice(Comparable etiqueta){
        if(!existeVertice(etiqueta)) {
            return -1;
        }
        int i = 0;
        for (Comparable etiq: getVertices().keySet()){
            if(etiq.equals(etiqueta)){
                break;
            }
            i++;
        }
        return i;

    }

    @Override
    public Double[] dijkstra(Comparable etiquetaOrigen){
        LinkedList<Comparable> verticesAux = new LinkedList<>(getVertices().keySet());
        verticesAux.remove(etiquetaOrigen);

        Double[][] costos = UtilGrafos.obtenerMatrizCostos(vertices);
        Double[] resultado = new Double[this.vertices.size()];

        LinkedList<Comparable> s = new LinkedList<>();
        s.add(etiquetaOrigen);

        int posOrigen = obtenerPosicionVertice(etiquetaOrigen);
        for (int i = 0; i < this.vertices.size(); i++) {
            resultado[i] = costos[posOrigen][i];
        }

        Comparable posMin;

        while (s.size() < this.vertices.size()) {
            double costoMin = Double.POSITIVE_INFINITY;
            posMin = null;
            for (Comparable i : verticesAux) { // Elegir w
                int posicion = obtenerPosicionVertice(i);
                if (resultado[posicion] < costoMin) {
                    costoMin = resultado[posicion];
                    posMin = i;
                }
            }

            s.add(posMin); // Agregar w a S

            verticesAux.remove(posMin); // Quitar w de V, para que sea V-S

            for (Comparable v : verticesAux){
                int posicion = obtenerPosicionVertice(v);
                int w = obtenerPosicionVertice(posMin);
                resultado[posicion] = Math.min(resultado[posicion], resultado[w] + costos[w][posicion]);

            }
        }
        return resultado;
    }

    @Override
    public Double[][] floyd() {
        Double[][] a = UtilGrafos.obtenerMatrizCostos(getVertices());
        for (int k = 0; k < this.vertices.size(); k++) {
            for (int i = 0; i < this.vertices.size(); i++) {
                if (k == i) {
                    continue;
                }
                for (int j = 0; j < this.vertices.size(); j++) {
                    if (i == j || j == k) {
                        continue;
                    }
                    if (a[i][k] + a[k][j] < a[i][j]) {
                        a[i][j] = a[i][k] + a[k][j];
                    }
                }
            }
        }
        return a;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] a = this.floyd();
        int cont = 0;
        double caminoMasLargo = 0;
        if (vertices.containsKey(etiquetaVertice)) {
            for (Comparable v : vertices.keySet()) {
                if (v.equals(etiquetaVertice)) {
                    break;
                }
                cont++;
            }
            for (int i = 0; i < this.vertices.size(); i++) {
                if (a[i][cont] > caminoMasLargo && a[i][cont]!= Double.MAX_VALUE) {
                    caminoMasLargo = a[i][cont];
                }
            }
            return caminoMasLargo;
        }
        return -1; //NO EXISTE EL VÉRTICE
    }

    @Override
    public boolean[][] warshall() {
        Double[][] a = this.floyd();
        boolean[][] b = new boolean[vertices.size()][vertices.size()];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                boolean conexion = a[i][j] < Double.MAX_VALUE;
                b[i][j] = conexion;
            }
        }
        return b;
    }
    
    //INVENTADO
    @Override
    public boolean esConexo(boolean[][] warshall){
        for(int i = 0; i<warshall.length; i++){
            for(int j = 0; j<warshall.length; j++){
                if(warshall[i][j] == false){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean esConexo() {
        for (TVertice vertice : vertices.values()) {
            desvisitarVertices();
            LinkedList<TVertice> visitados = new LinkedList<>();
            vertice.bpf(visitados); // Realizar un recorrido en profundidad desde cada vértice
            if (visitados.size() != vertices.size()) {
                return false; // Si no se visitaron todos los vértices, el grafo no es conexo
            }
        }
        return true;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }
    
    @Override
    public void desvisitarVertices() {
        for (TVertice v : this.vertices.values()) {
            v.setVisitado(false);
        }
    }
    
    @Override
    public LinkedList<TVertice> bea(){
        desvisitarVertices();
        LinkedList<TVertice> visitados = new LinkedList<TVertice>();
        for(TVertice v : this.vertices.values()){
            if (!visitados.contains(v)){
                v.bea(visitados);
            }
        }
        return visitados;
    }
    
    @Override
    public LinkedList<TVertice> bpf() {
        desvisitarVertices();
        LinkedList<TVertice> visitados = new LinkedList<TVertice>();
        for(TVertice v : this.vertices.values()){
            if (!visitados.contains(v)){
                v.bpf(visitados);
            }
        }
        return visitados;
    }

    @Override
    public LinkedList<TVertice> bpf(Comparable etiquetaOrigen) {
        return (bpf(this.vertices.get(etiquetaOrigen)));
    }

    @Override
    public LinkedList<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        LinkedList<TVertice> visitados = new LinkedList<TVertice>();
        vertice.bpf(visitados);
        return visitados;
    }
    
    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        if (vertices.containsKey(etiquetaOrigen)) {
            TVertice verticeOrigen = this.vertices.get(etiquetaOrigen);
            return verticeOrigen.todosLosCaminos(etiquetaDestino, new TCamino(verticeOrigen), new TCaminos());
        }
        return null;
    }
    
    @Override
    public boolean tieneCiclo(){
        desvisitarVertices();
        for (TVertice vertice : this.vertices.values()) {
            if(vertice.tieneCiclo()){
                return true;
            }
            desvisitarVertices();
        }
        return false;
    }
    
    @Override
    public LinkedList<TVertice> clasificacionTopologica(Comparable etiquetaVertice){
        desvisitarVertices();
        TVertice tVertice = buscarVertice(etiquetaVertice);
        LinkedList<TVertice> res = new LinkedList<>();

        tVertice.clasificacionTopologica(res);

        return res;
    }
    
    public boolean conectados(TVertice u, TVertice v) {
        this.desvisitarVertices();
        if (this.existeVertice(u.getEtiqueta()) && this.existeVertice(v.getEtiqueta())) {
            return this.buscarVertice(u.getEtiqueta()).conectadoCon(v);
        }
        return false;
    }

}
