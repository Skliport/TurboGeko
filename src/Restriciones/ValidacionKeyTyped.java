/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restriciones;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Alejandro
 */
public class ValidacionKeyTyped {
    public void validacionNombre(KeyEvent evento, JTextField txt, int limite){
        if (evento.getKeyChar() != KeyEvent.VK_SPACE && !Character.isAlphabetic(evento.getKeyChar())
                && evento.getKeyChar() != KeyEvent.VK_BACK_SPACE || txt.getText().length()>=limite) {
            evento.consume();
        }
    }
    public void validacionNumeroEntero(KeyEvent evento){
        if (!Character.isDigit(evento.getKeyChar())) {
            evento.consume();
        }
    }
    public void validacionNumeroReal(KeyEvent evento, JTextField txt,int limite){
        if (txt.getText().length()>=limite) {
            evento.consume();
        }else{
            if (!Character.isDigit(evento.getKeyChar())) {
                if (evento.getKeyChar() == '.' && txt.getText().length()>0) {
                    if (txt.getText().contains(".")) {
                        evento.consume();
                    }
                }else{
                    evento.consume();
                }
            }
        }
    }
    
}
