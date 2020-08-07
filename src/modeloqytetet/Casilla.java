/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author sandrogarcia97
 */
public abstract class Casilla {
    int numeroCasilla;
    int coste;
    TipoCasilla tipo;
   
    
    /*Constructor diseñado para casillas que no son de tipo calle, es decir la salida o la carcel por ejemplo*/
    public Casilla(int numeroCasilla, TipoCasilla tipo){
        this.numeroCasilla=numeroCasilla;
        this.tipo=tipo;
        
        if(tipo == TipoCasilla.IMPUESTO)
            this.coste = 200;
    }
    /*Constructor diseñado para las casilla de tipo calle, es decir en las que se puede edificar*/
    Casilla(int numeroCasilla, int coste){
        this.numeroCasilla=numeroCasilla;
        this.coste=coste;
        this.tipo=TipoCasilla.CALLE;
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    public int getCoste() {
        return coste;
    }
    
   public abstract int getNumHoteles();
   
   public abstract int getNumCasas();

   public TipoCasilla getTipo() {
        return tipo;
    }
    
    void setNumeroCasilla(int n){
        this.numeroCasilla= n;
    }
    
    void setCoste(int c){
        this.coste = c;
    }
    
    int precioTotalComprar(){
      return 0;
    }

    boolean soyEdificable(){
         if(this.tipo==TipoCasilla.CALLE)
             return true;
         else
             return false;
    }
 
    @Override
    public String toString(){
        return "\nCasilla{" + "Nº" + numeroCasilla + ", coste= " + coste + ", tipo= " + tipo + "}\n";
    }
}
