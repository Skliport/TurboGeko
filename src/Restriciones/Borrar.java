/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restriciones;

import javax.swing.JTextField;

/**
 *
 * @author Alejandro
 */
public class Borrar {
    public void borrar(JTextField[] txt){
        for (JTextField jTextField : txt) {
            jTextField.setText(null);
        }
    }
}
