/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author Sandro
 */
public class Especulador extends Jugador {
    public static int FactorEspeculador = 2;
    private int fianza;
    
    Especulador(Jugador jugador, int fianza){
        super(jugador);
        this.fianza = fianza;
    }
    
    @Override
    public int getFactorEspeculador(){
        return FactorEspeculador;
    }
    
    public int getFianza(){
        return fianza;
    }
    
    void pagarImpuestos(int cantidad){
        this.modificarSaldo(cantidad/2);
    }
    
    void irACarcel(Casilla casilla){
        
        if(!this.pagarFianza()){
           this.setCasillaActual(casilla);
            this.setEncarcelado(true);
        }
    }
    
    Especulador convertirme(int fianza){
        return this;
    }
    
    boolean pagarFianza(){
        boolean puedoPagar = false;
        
        if(this.getSaldo()> fianza){
            puedoPagar = true;
            this.modificarSaldo(-fianza);
        }
        return puedoPagar;
    }
    
    @Override
    public String toString() {
        return super.toString() + " es ESPECULADOR cuya fianza es " + this.fianza + " y su factor:" + this.getFactorEspeculador();
    }  
    
}
