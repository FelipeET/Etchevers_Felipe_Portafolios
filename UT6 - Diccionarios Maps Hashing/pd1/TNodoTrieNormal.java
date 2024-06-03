/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD1;

import java.util.LinkedList;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Felipe
 */
public class TNodoTrieNormal implements INodoTrie, Serializable {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrieNormal[] hijos;
    private LinkedList<String> paginas;
    private boolean esPalabra;

    public TNodoTrieNormal() {
        hijos = new TNodoTrieNormal[CANT_CHR_ABECEDARIO];
        this.esPalabra = false;
        paginas = new LinkedList<String>();
    }

    public void setPaginas(String pagina) {
        this.paginas.add(pagina);
    }

    TNodoTrieNormal obtenerHijo(char c) {
        return this.hijos[c - 'a'];
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrieNormal nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (indice >= 0 && indice < 26) {
                if (nodo.hijos[indice] == null) {
                    nodo.hijos[indice] = new TNodoTrieNormal();
                }
                nodo = nodo.hijos[indice];
            }
        }
        nodo.esPalabra = true;
    }

    public void insertarConIndice(String unaPalabra, String pagina) {
        TNodoTrieNormal nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (indice >= 0 && indice < 26) {
                if (nodo.hijos[indice] == null) {
                    nodo.hijos[indice] = new TNodoTrieNormal();
                }
                nodo = nodo.hijos[indice];
            }
        }
        nodo.paginas.add(pagina);
    }

    public String imprimirIndice(String s, TNodoTrieNormal nodo, String res) {
        if (nodo != null) {
            if (nodo.paginas != null) {
                s += s + " [ " + nodo.paginas.element() + " ] -";

            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {

                if (nodo.hijos[c] != null) {
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
        return res;
    }

    private void imprimir(String s, TNodoTrieNormal nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);

            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void imprimir() {

        imprimir("", this);
    }

    @Override
    public int buscar(String s) {
        int comparaciones = 0;
        TNodoTrieNormal nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (indice >= 0 && indice < 26) {
                if (nodo.hijos[indice] == null) {
                    return 0;
                }
                nodo = nodo.hijos[indice];
                comparaciones += 1;
            }
        }
        return comparaciones;
    }

    public TNodoTrieNormal buscarNodoTrie(String s) {
        TNodoTrieNormal nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (indice >= 0 && indice < 26) {
                if (nodo.hijos[indice] == null) {
                    return null;
                }
                nodo = nodo.hijos[indice];
            }
        }
        return nodo;
    }

    private void predecir(String cadena, LinkedList<String> palabras, TNodoTrieNormal nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(cadena);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c - 'a'] != null) {
                    predecir(cadena + (char) (c + 'a'), palabras, nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrieNormal nodoActual = this.buscarNodoTrie(prefijo);
        predecir(prefijo, palabras, nodoActual);
    }

}
