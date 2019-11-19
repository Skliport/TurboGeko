/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 * @param <T>
 */
public interface ICrudGeneric <T>{
    boolean ingresar(T generico);
    boolean actualizar(T generico);
    boolean eliminar(T generico);
    ArrayList<T> obtener();
}