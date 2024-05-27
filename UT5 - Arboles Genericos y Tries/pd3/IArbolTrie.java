/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ut5_pd3;

import java.util.LinkedList;

/**
 *
 * @author 
 */
public interface IArbolTrie {
    
    void imprimir();
    
    void imprimirIndice();
    
    void indizarLibro(String rutaArchivo);
    
    void insertarConIndice(String palabra, int pagina);
    
    void insertar(String palabra);
    
    int buscar(String palabra);

    LinkedList<String> predecir(String prefijo);
    
}
