/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//EXAMENP4P5
package modeloqytetet;
import java.util.Random;
import static java.lang.Math.random;

/**
 *
 * @author mingye
 */
public class Tramposo extends Jugador {
    private int veces = 0;
    private int cantidadNoReducida = 0;
    public Tramposo(Jugador jugador) {
        super(jugador);
    }
    public int trampa(int cantidad){
        Random r = new Random();
        int i = r.nextInt(2);
        if (i == 0){
            cantidad -= cantidad / 2;
            cantidadNoReducida +=cantidad*(-1);
            veces += 1;
        }
        return cantidad;
    }
    public void perdonar(){
        veces = 0;
        cantidadNoReducida = 0;
    }
    @Override
    public String toString(){
        String cadena = super.toString();
        if(veces > 0){
            cadena += "Tramposo, ha hecho "+Integer.toString(veces) + " trampas, y no ha pagado "+Integer.toString(cantidadNoReducida)+" euros" ;
        }
        return cadena;
    }
    
}
