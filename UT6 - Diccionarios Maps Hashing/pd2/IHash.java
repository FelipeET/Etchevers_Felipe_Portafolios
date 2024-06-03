/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UT6_PD2;

/**
 *
 * @author Felipe
 */
public interface IHash {
    
    public int buscar(int unaClave);
    
    public int insertar(int unaClave) throws HashTableOverloadException;
    
    public int funcionHashing(int unaClave);
    
}
