/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;


/**
 *
 * @author Administrador
 */
public class util {
    
    
    public static Image getIcone() {
        URL caminhoImagem = util.class.getResource("/images/logo_mini.png"); 
     
     ImageIcon icon = new ImageIcon(caminhoImagem);
     
     return icon.getImage();
    }
}
