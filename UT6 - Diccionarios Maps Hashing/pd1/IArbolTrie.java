/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UT6_PD1;

import java.util.LinkedList;

/**
 *
 * @author Felipe
 */
public interface IArbolTrie {
    
    void imprimir();
    
    int buscar(String palabra);

    void insertar(String palabra);

    LinkedList<String> predecir(String prefijo);    
    
}
