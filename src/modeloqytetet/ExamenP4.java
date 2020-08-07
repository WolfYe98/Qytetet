/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.Random;
import static java.lang.Math.random;

/**
 *
 * @author mingye
 * EXAMENP4P5
 */
public class ExamenP4 {
    private static Jugador jugador1;
    private static Jugador jugador2;
    public static void main(String args[]){
        Random r = new Random();
        int cantidad = r.nextInt(1000);
        jugador1 = new Jugador("Juan");
        jugador2 = new Tramposo(jugador1);
        for(int i = 0; i < 8; i++){
            cantidad = r.nextInt(1000);
            jugador1.modificarSaldo(-cantidad);
            jugador2.modificarSaldo(-cantidad);
        }
        System.out.println(jugador1.toString());
        System.out.println(((Tramposo)jugador2).toString());
        ((Tramposo)jugador2).perdonar();
        System.out.println(((Tramposo)jugador2).toString());

    }
}
