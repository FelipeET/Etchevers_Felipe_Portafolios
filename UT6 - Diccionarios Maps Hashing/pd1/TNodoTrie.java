/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Felipe
 */
public class TNodoTrie implements INodoTrie, Serializable {

    private final Map<Character, TNodoTrie> hijos;
    private boolean esPalabra;

    public TNodoTrie() {
        this.hijos = new HashMap<>();
        this.esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char c1 = unaPalabra.charAt(c);
            if(!nodo.hijos.containsKey(c1)){
                nodo.hijos.put(c1, new TNodoTrie());
            }
            nodo = nodo.hijos.get(c1);
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (Character character : nodo.hijos.keySet()) {
                imprimir(s+character, nodo.hijos.get(character));
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie TNodoTrie = this;
        int index = 0;
        while (TNodoTrie != null && index < s.length()){
            TNodoTrie = TNodoTrie.hijos.get(s.charAt(index));
            index++;
        }

        return TNodoTrie;
    }

    private void predecir(String s, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);
            }

            for (Character character : nodo.hijos.keySet()) {
                predecir(s+character, palabras, nodo.hijos.get(character));
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrie aux = this.buscarNodoTrie(prefijo);
        if (aux != null)
            aux.predecir(prefijo, palabras, aux);
    }

    @Override
    public int buscar(String s) {
        TNodoTrie nodoActual = this;
        int count = 0;
        for(char c : s.toCharArray()){
            TNodoTrie unHijo = nodoActual.obtenerHijo(c);
            if(unHijo == null) {
                return 0;
            }else{
                count++;
                nodoActual = unHijo;
            }
        }
        return count;
    }

    TNodoTrie obtenerHijo(char c){
        return this.hijos.get(c);
    }

}
