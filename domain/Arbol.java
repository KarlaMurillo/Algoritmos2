package domain;

import business.ClaseBusiness;
import java.io.FileNotFoundException;

/**
 *
 * @author Marco
 */

public class Arbol {

    public Nodo raiz;
    private String cadena;
    private int posicion;
    

    public Arbol() {
        
        this.raiz = null;
        this.posicion = 0;
        this.cadena = "";
        }//constructor

    public int obtenerBF(Nodo x) {
        
        if (x == null) {
            return -1;
        } else {
            return x.bF;
        }
    }//obtenerBF

    public Nodo parteIzquierda (Nodo n) {
        
        Nodo auxiliar = n.hijoDereco;
        n.hijoDereco = auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo = n;
        
        // Math.max para devolver el mayor de cero o más números
        n.bF = Math.max(obtenerBF(n.hijoIzquierdo), obtenerBF(n.hijoDereco)) + 1; //
        auxiliar.bF = Math.max(obtenerBF(auxiliar.hijoIzquierdo), obtenerBF(auxiliar.hijoDereco));

        return auxiliar;
    }// parteIzquierda

    public Nodo parteDerecha (Nodo n) {
        
        Nodo auxiliar = n.hijoIzquierdo;
        n.hijoIzquierdo = auxiliar.hijoDereco;
        auxiliar.hijoDereco = n;

        n.bF = Math.max(obtenerBF(n.hijoIzquierdo), obtenerBF(n.hijoDereco));
        auxiliar.bF = Math.max(obtenerBF(auxiliar.hijoIzquierdo), obtenerBF(auxiliar.hijoDereco));

        return auxiliar;
    }//parteDerecha

    
    //coloca de Izquierda a Derecha
    public Nodo pID (Nodo n) {
        
        Nodo temp;
        n.hijoIzquierdo = parteIzquierda(n.hijoIzquierdo);
        temp = parteDerecha(n);

        return temp;

    }// pDI

    //coloca de derecha a Izquierda
    public Nodo pDI (Nodo n) {
        
        Nodo temp;
        n.hijoDereco = parteDerecha(n.hijoDereco);
        temp = parteIzquierda(n);

        return temp;

    }//pDI
    
    public Nodo insertarI(Nodo nodo, Nodo subArbol) {
        Nodo nuevoPadre = subArbol;

        if (nodo.getCadena().compareTo(subArbol.getCadena()) < 0) {
            if (subArbol.hijoIzquierdo == null) {
                subArbol.hijoIzquierdo = nodo;
                subArbol.hijoIzquierdo.setCantPosiciones(subArbol.hijoIzquierdo.getCantPosiciones() + this.posicion + "-");
                this.posicion++;
            } else {
                subArbol.hijoIzquierdo = insertarI(nodo, subArbol.hijoIzquierdo);

                if ((obtenerBF(subArbol.hijoIzquierdo) - obtenerBF(subArbol.hijoDereco) == 2)) {
                    if (nodo.getCadena().compareTo(subArbol.hijoIzquierdo.getCadena()) < 0) {
                        nuevoPadre = parteDerecha(subArbol);
                    } else {

                        nuevoPadre = pID (subArbol);

                    }
                }
            }
        } else if (nodo.getCadena().compareTo(subArbol.getCadena()) > 0) {
            if (subArbol.hijoDereco == null) {
                subArbol.hijoDereco = nodo;
                subArbol.hijoDereco.setCantPosiciones(subArbol.hijoDereco.getCantPosiciones() + this.posicion + "-");
                this.posicion++;
            } else {
                subArbol.hijoDereco = insertarI(nodo, subArbol.hijoDereco);

                if ((obtenerBF(subArbol.hijoIzquierdo) - obtenerBF(subArbol.hijoDereco) == -2)) {
                    if (nodo.getCadena().compareTo(subArbol.hijoDereco.getCadena()) > 0) {
                        nuevoPadre = parteIzquierda (subArbol);
                    } else {

                        nuevoPadre = pDI(subArbol);

                    }
                }
            }
        } else {
            subArbol.setCantPosiciones(subArbol.getCantPosiciones() + this.posicion + "-");
            this.posicion++;
        }
        //iniciar
        if ((subArbol.hijoIzquierdo == null) && (subArbol.hijoDereco != null)) {
            subArbol.bF = subArbol.hijoDereco.bF + 1;
        } else if ((subArbol.hijoDereco == null) && (subArbol.hijoIzquierdo != null)) {
            subArbol.bF = subArbol.hijoIzquierdo.bF + 1;
        } else {
            subArbol.bF = Math.max(obtenerBF(subArbol.hijoIzquierdo), obtenerBF(subArbol.hijoDereco)) + 1;
        }

        return nuevoPadre;
    }//insertarI

    public void insertar(String n) {
        Nodo nuevoNodo = new Nodo(n);
        if (this.raiz == null) {
            this.raiz = nuevoNodo;
            this.raiz.setCantPosiciones(this.raiz.getCantPosiciones() + this.posicion + "-");
            this.posicion++;
        } else {
            this.raiz = insertarI(nuevoNodo, raiz);

        }//else-if
    }//insertar

    public void guardarNodo (Nodo r, String ruta) throws FileNotFoundException {

        ClaseBusiness claseBusiness = new ClaseBusiness(ruta);
        if (r != null) {

            if (r.getCadena().length() < 4 || r.getCantPosiciones().length() < 5) {
            } else {
                claseBusiness.guardarArbol(r);
            }
            
            guardarNodo(r.hijoIzquierdo, ruta);
            guardarNodo(r.hijoDereco, ruta);
        } //else-if
    }//guardarNodo
    
}//class
