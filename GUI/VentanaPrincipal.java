package GUI;

import domain.Arbol;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Marco
 */

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton jbtnAbrirArchivo;
    private JButton jbtnGuardarArchivo;
    private JScrollPane scroll;
    private PintarArbol pintarArbol;
    private Arbol arbol;
    private JFileChooser chooser;

    public  VentanaPrincipal () {

        super("Segundo Proyecto de Algoritmos");
        Dimension dim= super.getToolkit().getScreenSize();
        super.setSize(dim);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        startComponents();

    }//construtor

    private void startComponents() {
        arbol = new Arbol ();

        this.jbtnAbrirArchivo = new JButton("Abrir archivo");
        this.jbtnAbrirArchivo.setBounds(10, 10, 137, 30);
        this.jbtnAbrirArchivo.addActionListener(this);
        this.jbtnAbrirArchivo.setBackground(Color.WHITE);
        
        this.jbtnGuardarArchivo = new JButton("Guardar arbol");
        this.jbtnGuardarArchivo.setBounds(10, 55, 137, 30);
        this.jbtnGuardarArchivo.addActionListener(this);
        this.jbtnGuardarArchivo.setBackground(Color.white);
        this.jbtnGuardarArchivo.setEnabled(false);
        
        this.add(this.jbtnAbrirArchivo);
        this.add(this.jbtnGuardarArchivo);
 
        
    }//startComponents

    public void pintar() {
        
        this.pintarArbol = new PintarArbol(this.arbol);
        this.scroll = new JScrollPane();
        this.scroll.setBounds(new Rectangle(10, 100, 1345, 600));
        this.scroll.setViewportView(this.pintarArbol);
        this.scroll.getViewport().setView(this.pintarArbol);
        this.pintarArbol.setPreferredSize(new Dimension(4500, 4500));
        this.pintarArbol.repaint();
        this.pintarArbol.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.add(this.scroll);

    }//pintar

    public void pintarArbol(String p) {
        String[] array = p.split("[ \n]");
        for (int i = 0; i < array.length; i++) {
            this.arbol.insertar(array[i]);
        }//for
    }//pintarArbol

    public String abrirArchivo() throws IOException {
        String inf = "";
        chooser = new JFileChooser();
        String ruta;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileReader fileReader = null;
            try {
                ruta = chooser.getSelectedFile().getAbsolutePath();
                File file = new File(ruta);

                fileReader = new FileReader(file);

                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    inf += line + "\n";
                }//while
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch
        } else {

        }

        return inf;
    }//abrirArchivo

    public String abrirRuta() throws IOException {

        chooser = new JFileChooser();
        String ruta = "";
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            ruta += chooser.getSelectedFile().getAbsolutePath();

        }
        return ruta;
    }//abrirRuta

    private void guardarFileChooser() {
        String ruta;
        chooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("txt", "TXT");
        chooser.setFileFilter(extensionFilter);
        try {
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = chooser.getSelectedFile().getAbsolutePath();
              //  arboll.guardarNodo(arboll.raiz, ruta);
            }//if
        } catch (Exception ex) {
        }//try-catch

    }//guardarFileChooser

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.jbtnAbrirArchivo) {

            try {
                pintarArbol(abrirArchivo());
                pintar();

            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.jbtnGuardarArchivo.setEnabled(true);
        }//btnOpenFile

        if (e.getSource() == this.jbtnGuardarArchivo) {
            guardarFileChooser();
        }//btnSaveFile


        }
}//Class

