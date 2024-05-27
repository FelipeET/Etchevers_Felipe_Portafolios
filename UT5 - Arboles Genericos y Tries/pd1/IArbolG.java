
package ut5_pd1;

public interface IArbolG<T> {
    
    /**
     * Obtiene la raíz del árbol
     * @return nodo raiz del arbol
     */
    INodoAG<T> getRaiz();
    
    /**
     * Devuelve la raiz del arbol
     */
    void setRaiz(INodoAG<T> raiz);
    
    /**
     * Inserta un nodo como hijo de otro
     * @param unaEtiqueta etiqueta del nodo a insertar
     * @param etiquetaPadre etiqueta del nodo que deseo sea el padre
     * @return true en caso de que se haya insertado correctamente, en caso contrario false
     */
    boolean insertar(String unaEtiqueta, Comparable etiquetaPadre);

    /**
     * Busca el nodo que contiene la etiqueta pasada por parametro
     * @param unaEtiqueta etiqueta del nodo buscado.
     * @return si encuentra el nodo lo devuelve, en caso contrario devuelve null
     */
    INodoAG<T> buscar(Comparable unaEtiqueta);

    /**
     * Lista las etiquetas de todos los nodos del arbol.
     * @return string, que contiene todas las etiquetas de los nodos del arbol
     */
    String listarIndentado();
    
}
