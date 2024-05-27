package ut5_pd3;

import java.util.LinkedList;

/**
 *
 * @author
 */
public class TNodoTrie implements INodoTrie {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private LinkedList<String> paginas;
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        this.esPalabra = false;
        paginas = new LinkedList<String>();
    }

    public void setPaginas(String pagina) {
        this.paginas.add(pagina);
    }

    TNodoTrie obtenerHijo(char c) {
        return this.hijos[c - 'a'];
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (indice >= 0 && indice < 26) {
                if (nodo.hijos[indice] == null) {
                    nodo.hijos[indice] = new TNodoTrie();
                }
                nodo = nodo.hijos[indice];
            }
        }
        nodo.esPalabra = true;
    }

    @Override
    public void insertarConIndice(String unaPalabra, String pagina) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (indice >= 0 && indice < 26) {
                if (nodo.hijos[indice] == null) {
                    nodo.hijos[indice] = new TNodoTrie();
                }
                nodo = nodo.hijos[indice];
            }
        }
        nodo.paginas.add(pagina);
    }

    @Override
    public String imprimirIndice(String s, TNodoTrie nodo, String res) {
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

    private void imprimir(String s, TNodoTrie nodo) {
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
        TNodoTrie nodo = this;
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

    @Override
    public TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
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

    private void predecir(String cadena, LinkedList<String> palabras, TNodoTrie nodo) {
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
        TNodoTrie nodoActual = this.buscarNodoTrie(prefijo);
        predecir(prefijo, palabras, nodoActual);
    }

}
