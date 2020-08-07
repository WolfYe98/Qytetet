/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;


public class PruebaQytetet {
    
 public static ArrayList<Sorpresa> mazo = new ArrayList();
 public static Tablero eltablero = new Tablero(); 
 public static ArrayList<Jugador> jugadores = new ArrayList();

    public static void main(String[] args) {
        Qytetet q=Qytetet.getInstance();
        ArrayList <String> nombres = new ArrayList();
        nombres.add("Ana");
        nombres.add("Pedro");
        q.inicializarJuego(nombres);
        System.out.println(q.toString()); 
    }
    
    private static void InicializarSorpresas(){  //Metodos static, solo los puede llamar la clase
        mazo.add(new Sorpresa ("¡Ve a la carcel!", 9, TipoSorpresa.IRACASILLA));
        
        mazo.add(new Sorpresa ("Sal de la carcel con esta carta",0, TipoSorpresa.SALIRCARCEL));
        
        mazo.add(new Sorpresa ("Paga o cobra la cantidad indicada", 4, TipoSorpresa.PAGARCOBRAR));
        
        mazo.add(new Sorpresa ("Ve a la casilla indicada", 2, TipoSorpresa.IRACASILLA));
        
        mazo.add(new Sorpresa ("Paga o cobra la cantidad indicada a todos los jugadores,",3, TipoSorpresa.PORJUGADOR));
        
    }
    //Metodo que devuelve las sorpresas del mazo cuyo valor sea mayor que 0
    private static ArrayList getMayor(){
        ArrayList<Sorpresa> mayores = new ArrayList();
        for(Sorpresa s: mazo){      //La variable tipo sorpresa se mete dentro del mazo con el simbolo ":" y lo recorre
            if(s.getValor()>0)
                    mayores.add(s);
        }
        return mayores;
    }
    //Metodo que devuelve las sorpresas del mazo cuyo tipo sea "ir a casilla"
    private static ArrayList getIrCasilla(){
        ArrayList<Sorpresa> irCasilla = new ArrayList();
        for(Sorpresa s: mazo){
            if(s.getTipo()==TipoSorpresa.IRACASILLA)
                irCasilla.add(s);
        }
        return irCasilla;
    }
    //Metodo que devuelve las sorpresas del mazo cuyo tipo sean iguales al argumento
    private static ArrayList IgualTipoSorpresa(TipoSorpresa tipo){
        ArrayList<Sorpresa> IgualTipoSorpresa = new ArrayList();
        for(Sorpresa s: mazo){
            if(s.getTipo()==tipo)
                IgualTipoSorpresa.add(s);
        }
        return IgualTipoSorpresa;
    }
     
}