package domain;

/**
 *
 * @author Marco
 */
public class Nodo {
    
    int bF;
    private String cadena;
    private String cantPosiciones; //cantidad de posiciones
    public Nodo hijoIzquierdo; 
    public Nodo hijoDereco; 

    public Nodo (String cadena) {
        this.bF = 0;
        this.hijoIzquierdo = null;
        this.hijoDereco = null;
        this.cadena = cadena;
        this.cantPosiciones = "";
    }//constructor

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getCantPosiciones() {
        return cantPosiciones;
    }

    public void setCantPosiciones(String cantPosiciones) {
        this.cantPosiciones = cantPosiciones;
    }

}//class
