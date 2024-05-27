/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ut5_pd1;

/**
 *
 * @author 
 */
public interface INodoAG<T> {
    
    /**
     * Obtiene la etiqueta.
     * @return etiqueta de este nodo.
     */
    Comparable getEtiqueta();
    
    /**
     * Obtiene el primer hijo.
     * @return nodo que se encuentra mas a la izquierda de los hijos de este nodo
     */
    INodoAG<T> getPrimerHijo();
    
    /**
     * Obtiene el hermano derecho.
     * @return nodo que se encuentra a la derecha.
     */
    INodoAG<T> getHermano();

    /**
     * Establece el primer hijo.
     * @param nuevoHijo nodo que toma lugar como hijo
     */
    void setPrimerHijo(INodoAG<T> hijo);

    /**
     * Establece el hermano derecho.
     * @param nuevoHermano nodo que toma lugar como hermano derecho
     */
    void setHermano(INodoAG<T> hermano);
    
     /**
     * Inserta un nodo como hijo de otro
     * @param unaEtiqueta etiqueta del nodo a insertar
     * @param etiquetaPadre etiqueta del padre del nodo a insertar, vacío si deseo insertar la raiz.
     * @return si se inserta correctamente devuelve true, en caso contrario false
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
    String listarIndentado(String listado);   
    
}
