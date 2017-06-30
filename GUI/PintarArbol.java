
package GUI;

import domain.Arbol;
import domain.Nodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Marco
 */

public class PintarArbol extends JPanel {

    private Arbol mArbol;
    private Nodo mNodo;
    private Graphics2D graphics2D;
    private BufferedImage bufferedImage;
    private int x;
    private int y;
    
    public PintarArbol(Arbol mArbol) {
        super();
        this.mArbol = mArbol;
        this.mNodo = this.mArbol.raiz;
        try {
            this.bufferedImage = ImageIO.read(getClass().getResourceAsStream("/assets/fondo.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(PintarArbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.graphics2D = (Graphics2D) this.bufferedImage.getGraphics();
        
    }//constructor

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, 3000, 3000, null);
        pintarArbol(g, mNodo, 600, 20, 130, 10, 0);
    }//paintComponent

    private void pintarArbol(Graphics g, Nodo nodo, int x, int y, int xf, int yf, int nivel) {
        if (nodo == null) {
            return;
        }
        g.setColor(Color.GRAY);//Color de capsula

        if (nodo.hijoIzquierdo != null) {
            
            //direccion de las lineas
            g.drawLine(x, y, x - xf + (nivel * 2) - 40, (y + yf) + 100);

        }//leftSon
        if (nodo.hijoDereco != null) {

            g.drawLine(x, y, x + xf - (nivel * 2) + 40, (y + yf) + 100);
            
        }//RightSon

        g.fillRect(x -70, y - 15, 130, 50); //Forma que tendrá la capsula
        
        g.setColor(Color.WHITE);
        
        String nodoUno = nodo.getCantPosiciones();
        nodoUno = nodoUno.substring(0, nodoUno.length() - 1);

        g.drawString(nodo.getCadena() + " [ " + nodoUno + " ]", x - 50, y + 15);
        
        //pintar hacia la derecha
        pintarArbol(g, nodo.hijoIzquierdo, (int) (x - xf) + 20, (y + yf) + 100, ((xf+20)+ nivel * 2)-20 , yf+77, nivel + 1);
        //pintar hacia la izquierda
        pintarArbol(g, nodo.hijoDereco,(int) (x + xf) - 20, (y + yf) + 100, ((xf+20)- nivel * 2) - 20, yf+15, nivel + 1);

    }//paintTree

}//class