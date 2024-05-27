
package ut5_pd3;

import java.util.LinkedList;

/**
 *
 * @author 
 */
public class TArbolTrie implements IArbolTrie {
    
    private TNodoTrie raiz;
    
    @Override
    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(palabra);
    }

    @Override
    public void insertarConIndice(String palabra, int pagina) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertarConIndice(palabra, "" + pagina);
    }
    
    @Override
    public void imprimirIndice(){
        if (raiz != null) {
            System.out.println(raiz.imprimirIndice("",raiz,""));
        }
    }
    
    @Override
    public void indizarLibro(String rutaArchivo){
        String[] libro = ManejadorArchivosGenerico.leerArchivo(rutaArchivo);
        int i = 1;
        int pagina = 1;
        for (String lineas : libro){
            for (int j = 0; j<lineas.length(); j++){
                String palabra = "";
                if(Character.isAlphabetic(lineas.substring(j, j+1).toLowerCase().charAt(0))){
                    palabra += lineas.substring(j, j+1);
                }
                if (i == 50){
                    pagina += 1;
                    i = 1;
                } else {
                    i++;
                }
                if(lineas.substring(j, j+1).toLowerCase().compareTo(" ") == 0){
                    TNodoTrie aIndentar = raiz.buscarNodoTrie(palabra);
                    aIndentar.setPaginas(pagina + "");
                    palabra = "";
                }
            }    
        }
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra);
        }
        return 0;
    }
    
    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> lista = new LinkedList<>();
        if (raiz == null) {
            return lista;
        } else {
            raiz.predecir(prefijo, lista);
        }
        return lista;
    }
    
}
