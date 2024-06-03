
package UT6_PD1;

/**
 *
 * @author Felipe
 */
public class Main {
    
    public static void main(String[] args) {
        
        TArbolTrie tArbolTrieHashMap = new TArbolTrie();
        TArbolTrieNormal tArbolTrie = new TArbolTrieNormal();

        for (String linea : ManejadorArchivosGenerico.leerArchivo("src\\com\\ut6\\pd1\\palabras.txt")) {
            tArbolTrieHashMap.insertar(linea);
            tArbolTrie.insertar(linea);
        }
        MedibleTArbolTrieHashMap mtathm = new MedibleTArbolTrieHashMap(tArbolTrieHashMap);
        Medicion mtathmMedicion = mtathm.medir();
        mtathmMedicion.print();


    }
    
    
}
