package org.example;

import javax.swing.*;
/**
 * Desarrollado por Magucho (Edgar M Gómez P.) Back-end Developer
 */
public class App {
    public static void main( String[] args ) {// clase principal

        Efecto_1 llamado = new Efecto_1();
        llamado.setSize(800, 500);
        llamado.setUndecorated(true);
        llamado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        llamado.setVisible(true);

    }
}
