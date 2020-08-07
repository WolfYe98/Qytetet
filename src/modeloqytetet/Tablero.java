/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;
/**
 *
 * @author Sandro
 */
public class Tablero {
    
    private static ArrayList<Casilla> casillas;
    private Casilla carcel;
    
    Tablero(){
        inicializar();
    }
    
    Casilla getCarcel(){
        return this.carcel;
    }
    
    @Override
    public String toString(){
        return  casillas.toString() + "\nCarcel: " + carcel.toString();
    }
    
    boolean esCasillaCarcel(int numeroCasilla){
        
        boolean resultado=false;
        
        if(carcel.getNumeroCasilla() == numeroCasilla)
                resultado=true;
        return resultado;        
    }
    
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    
    public Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        int numCasilla = casilla.getNumeroCasilla();
        numCasilla = (numCasilla + desplazamiento) %casillas.size();
        
        return this.casillas.get(numCasilla);
    }
    
    /*Metodo para fabricar el tablero:*/
    public void inicializar(){
        this.casillas = new ArrayList();
        
        int i = 0;
        ArrayList<TituloPropiedad> titulos = new ArrayList();
        titulos.add(new TituloPropiedad("Ronda de Valencia", 50,(float)0.1,150,250));
        titulos.add(new TituloPropiedad("Plaza Lavapies", 50, (float) 0.1,150,250));
        titulos.add(new TituloPropiedad("Avenida Reina Victoria", 70,(float) 0.12,210,310));
        titulos.add(new TituloPropiedad("Glorieta de Bilbao", 75,(float) 0.13,250,350));
        titulos.add(new TituloPropiedad("Avenida Felipe II", 75,(float) 0.13,260,360));
        titulos.add(new TituloPropiedad("Calle Cea Bermudez", 80,(float) 0.14,360,460));
        titulos.add(new TituloPropiedad("Calle Bailen", 85,(float) 0.15,380,500));
        titulos.add(new TituloPropiedad("Plaza de España", 87,(float) 0.16,510,550));
        titulos.add(new TituloPropiedad("Puerta del Sol", 90, (float)0.17,550,600));
        titulos.add(new TituloPropiedad("Gran Via", 93, (float)0.18,650,650));
        titulos.add(new TituloPropiedad("Paseo de la Castellana", 95, (float)0.19,750,700));
        titulos.add(new TituloPropiedad("Paseo del Prado",100, (float)0.20,850,750));
        
        /*-------------------------------------------------------------*/
        
        casillas.add(new OtraCasilla(0,TipoCasilla.SALIDA));
        casillas.add(new Calle(1,500, titulos.get(i++)));
        casillas.add(new Calle(2,500,titulos.get(i++)));
        casillas.add(new OtraCasilla(3,TipoCasilla.SORPRESA));
        casillas.add(new OtraCasilla(4,TipoCasilla.CARCEL));
        carcel = this.casillas.get(4);
        casillas.add(new Calle(5,700,titulos.get(i++)));
        casillas.add(new Calle(6,800,titulos.get(i++)));
        casillas.add(new OtraCasilla(7,TipoCasilla.SORPRESA));
        casillas.add(new Calle(8,850,titulos.get(i++)));
        casillas.add(new OtraCasilla(9, TipoCasilla.PARKING));
        casillas.add(new Calle(10,1050,titulos.get(i++)));
        casillas.add(new Calle(11,1250,titulos.get(i++)));
        casillas.add(new OtraCasilla(12, TipoCasilla.SORPRESA));
        casillas.add(new Calle(13,1450,titulos.get(i++)));
        casillas.add(new Calle(14,1750,titulos.get(i++)));
        casillas.add(new OtraCasilla(15, TipoCasilla.JUEZ));
        casillas.add(new Calle(16,1950,titulos.get(i++)));
        casillas.add(new Calle(17,2150,titulos.get(i++)));
        casillas.add(new OtraCasilla(18, TipoCasilla.IMPUESTO));
        casillas.add(new Calle(19, 2700, titulos.get(i++)));
    }   
}