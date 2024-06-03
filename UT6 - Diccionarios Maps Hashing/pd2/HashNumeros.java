/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UT6_PD2;

import java.util.Arrays;

/**
 *
 * @author Felipe
 */
public class HashNumeros implements IHash{

    public static int comparaciones_insercion;
    public static int comparaciones_busqueda_exitosa;
    public static int comparaciones_busqueda_fallida;

    private final int[] table;

    public HashNumeros(int size) {
        this.table = new int[size];
        Arrays.fill(this.table, -1);
    }

    @Override
    public int buscar(int unaClave) {
        int i = funcionHashing(unaClave);
        int j = i;
        int comparaciones_busqueda = 0;
        while(this.table[j] != -1){
            comparaciones_busqueda++;
            if(this.table[j] == unaClave){
                comparaciones_busqueda_exitosa += comparaciones_busqueda;
                return this.table[j];
            }
            j = (j+1) % this.table.length;
            if(j==i){
                break;
            }
        }
        comparaciones_busqueda_fallida += comparaciones_busqueda;
        return -1;
    }

    @Override
    public int insertar(int unaClave) throws HashTableOverloadException{
        int i = funcionHashing(unaClave);
        int j = i;
        while(this.table[j] != -1){
            comparaciones_insercion++;
            j = (j+1) % this.table.length;
            if(j==i){
                throw new HashTableOverloadException("La tabla se ha sobrecargado!");
            }
        }
        this.table[j] = unaClave;
        return j;
    }

    @Override
    public int funcionHashing(int unaClave) {
        return unaClave % this.table.length;
    }

    public int getSize(){
        return this.table.length;
    }
}
