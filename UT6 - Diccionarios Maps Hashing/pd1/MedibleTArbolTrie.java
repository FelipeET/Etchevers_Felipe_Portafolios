/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD1;

/**
 *
 * @author Felipe
 */
public class MedibleTArbolTrie extends Medible{

    TArbolTrieNormal tArbolTrie;

    public MedibleTArbolTrie(TArbolTrieNormal tArbolTrie) {
        this.tArbolTrie = tArbolTrie;
    }

    @Override
    public void ejecutar(Object... params) {
        for (String s : this.tArbolTrie.predecir("")) {
            this.tArbolTrie.predecir(s);
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.tArbolTrie;
    }
}
