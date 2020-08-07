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
public class OtraCasilla extends Casilla {
    public OtraCasilla(int numCasilla, TipoCasilla tipo){
        super(numCasilla, tipo);
    }
    
        @Override
    public int getNumHoteles() {
        return 0;
    }

    @Override
    public int getNumCasas() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + "Otra casilla{" + '}';
    }
    
}
