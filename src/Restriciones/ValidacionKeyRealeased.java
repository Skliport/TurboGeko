/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restriciones;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author Alejandro
 */
public class ValidacionKeyRealeased {
    public void validacionTamañoColor(JTextField txt,int tamano){
        if (txt.getText().length()>tamano) {
            txt.setBackground(Color.RED);
        }else{
            txt.setBackground(Color.WHITE);
        }
    }
}
