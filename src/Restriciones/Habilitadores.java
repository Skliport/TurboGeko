/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restriciones;

import java.awt.Component;

/**
 *
 * @author Alejandro
 */
public class Habilitadores {
    public void cambiarEstadoComponentes(Component[] componen,boolean estado){
        for (Component component : componen) {
            component.setEnabled(estado);
        }
    }
}
