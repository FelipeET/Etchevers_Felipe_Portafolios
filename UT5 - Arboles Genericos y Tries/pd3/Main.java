
package ut5_pd3;

/**
 *
 * @author 
 */
public class Main {
    
    public static void main(String[] args){
        
        TArbolTrie trie = new TArbolTrie();
        
        String[] palabras = ManejadorArchivosGenerico.leerArchivo("ut5\\pd3\\PalabrasIndice.txt");
        for (String p : palabras){
            trie.insertar(p);
        }
        
        trie.indizarLibro("ut5\\pd3\\libro.txt");
        
        trie.imprimirIndice();
    }
    
}