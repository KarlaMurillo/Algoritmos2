package data;

import domain.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author Marco
 */

public class ClaseData {
    
    private String archivo;
    private String cadena;

    public ClaseData(String archivo) {
        this.archivo = archivo;
        this.cadena = "";
    }//constructor

    public void guardarArbol( Nodo nodo) throws FileNotFoundException {

        File file = new File(this.archivo);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        String temp = nodo.getCantPosiciones();
        temp = temp.substring(0, temp.length() - 1);
        printStream.println(nodo.getCadena() + "#" + temp);

    }//guardarArbol

    public String obtenerArbol() throws FileNotFoundException, IOException {
        String resultado = "";
        File file = new File(this.archivo);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {

            resultado += line + "&";

        }//while
        resultado = resultado.substring(0, resultado.length() - 1);
        return resultado;
    }//obtenerArbol

}//class
