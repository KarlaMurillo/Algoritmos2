package business;

import data.ClaseData;
import domain.Nodo;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Marco
 */
public class ClaseBusiness {
    
    private ClaseData claseData;

    public ClaseBusiness(String archivo) {
        this.claseData = new ClaseData(archivo);
    }//constructor

    public void guardarArbol(Nodo nodo) throws FileNotFoundException {
        this.claseData.guardarArbol(nodo);

    }//gurdarArbol

    public String obtenerArbol() throws FileNotFoundException, IOException {
        return this.claseData.obtenerArbol();

    }//obtenerArbol

}//class
