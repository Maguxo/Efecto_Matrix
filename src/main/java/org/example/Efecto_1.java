package org.example;

import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
/*
Desarrollado por Magucho (Edgar M Gómez P.) Back-end Developer
 */
public class Efecto_1 extends JFrame implements ActionListener {

    private Random aleatorio = new Random(); // clase para obtenr caracteres aleatorias
    private Dimension screenSize; // tamaño del panel
    private JPanel graphicsPanel;// base donde se aloja el proyecto
    private final static int ini = 20; // Información de posición del caracter
    private int[] almArr; // arreglo para almacenar ciclo de la columna
    private int lines; // filas
    private int columns; // columnas

    public Efecto_1() {
        initComponents();
    }
    private void initComponents() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        lines = screenSize.height / ini;
        columns = screenSize.width / ini;
        almArr = new int[columns + 1]; // declaración el ciclo
        aleatorio = new Random(); // declaración clase aleatorio

        for (int i = 0; i < almArr.length; i++) {
            almArr[i] = aleatorio.nextInt(lines);
        }

        new Timer(100, this).start(); // 10 cuadros por segundo

        setLayout(new BorderLayout());
        graphicsPanel = new GraphicsPanel();//dibuja lo que le hemos insertado
        add(graphicsPanel, BorderLayout.CENTER);

// Establecimiento del cursor para que sea invisible
        KeyPressListener keyPressListener = new KeyPressListener();
        this.addKeyListener(keyPressListener);
        int width,hight;
        width = (screenSize.width-800)/2;  //anchura de la pantalla
        hight = (screenSize.height-500)/2; // altura de pantalla
        this.setLocation(width, hight); // ubicación de pantalla
    }
    private char getChr() { // caracteres aleatorios

        return (char) (aleatorio.nextInt(160) + 218);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        graphicsPanel.repaint();
        System.out.println("OK");
    }
    private class GraphicsPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(getFont().deriveFont(Font.BOLD)); // fuente de letra negrilla
            g2d.setColor(Color.BLACK); // color de fondo (negro)
            g2d.fillRect(0, 0, screenSize.width, screenSize.height);
            int runColumn = 0; // columna
            for (int x = 0; x < screenSize.width; x += ini) {
                int endPos = almArr[runColumn];
                g2d.setColor(new Color(85,233,58));
                g2d.drawString(String.valueOf(getChr()), x, endPos * ini);
                int color = 0;
                for (int j = endPos - 15; j < endPos; j++) {
                    color += 20;  // gradiente de color caracteres
                    if (color > 108) {
                        color = 0;
                    }
                    g2d.setColor(new Color(0, color, 0));
                    g2d.drawString(String.valueOf(getChr()), x, j * ini);
                }
                almArr[runColumn] += aleatorio.nextInt(6); // la posicion salta dentro de la pantalla (vertical) aleatoriamente

                if (almArr[runColumn] * ini > getHeight()) {// la posición salta dentro de la pantalla  (Horizontal) aleatoriamente
                    almArr[runColumn] = aleatorio.nextInt(lines);
                }
                runColumn++;
                System.out.println(runColumn);
            }
        }
    }
    private static class KeyPressListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }
}