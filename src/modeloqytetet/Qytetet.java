/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import GUIQytetet.Dado;
import java.util.Random;
/**
 *
 * @author sandro
 */
public class Qytetet {
    /**********************************************/
    /*Estructura singleton: clases que solo pueden tener una instancia*/
    private static Qytetet instance;
    public static int MAX_JUGADORES= 4;
    public static int MAX_CARTAS=10;
    public static int MAX_CASILLAS=20;
    public static int PRECIO_LIBERTAD=200;
    public static int SALDO_SALIDA=1000;
    private Sorpresa cartaActual;
    private Jugador jugadorActual;
    private Dado dado;
    private static ArrayList<Sorpresa> mazo;
    private static Tablero eltablero; 
    private static ArrayList<Jugador> jugadores;
    
    /*Constructor privado, impide que se pueda instanciar desde otra clase*/
    private Qytetet(){
        
        inicializarTablero();       
        inicializarCartasSorpresa();        
        Dado dado = GUIQytetet.Dado.getInstance();        
        
    }
    
    private void inicializarTablero(){
        eltablero = new Tablero();
    }
    
    public static Qytetet getInstance(){
        instance = new Qytetet();
        return instance;
    }
    /**********************************************/

    
    public boolean aplicarSorpresa(){
        boolean esCarcel, tienePropietario=false;
        Casilla nuevaCasilla;
        
        if(cartaActual.getTipo()==TipoSorpresa.PAGARCOBRAR)
            jugadorActual.modificarSaldo(cartaActual.getValor());
        else if(cartaActual.getTipo()==TipoSorpresa.IRACASILLA){
            esCarcel = eltablero.esCasillaCarcel(cartaActual.getValor());
            
            if(esCarcel)
                this.encarcelarJugador();
            else{
                nuevaCasilla = eltablero.obtenerCasillaNumero(cartaActual.getValor());
                tienePropietario = jugadorActual.actualizaPosicion(nuevaCasilla);
            }
        }else if(cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL){
            jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
        }else if(cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
            for(Jugador j : jugadores){
                if(jugadorActual != j){
                    j.modificarSaldo(-(cartaActual.getValor()));
                    jugadorActual.modificarSaldo(-(cartaActual.getValor()));
                }
            }  
        }else if(cartaActual.getTipo() == TipoSorpresa.CONVERTIRME)
            jugadorActual = jugadorActual.convertirme(cartaActual.getValor());
    
        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL)
            jugadorActual.setCartaLibertad(cartaActual);
        else
            mazo.add(cartaActual);
 
        return tienePropietario;
    }
    
    public Tablero getTablero(){
        return eltablero;
    }
    
    public Sorpresa setCartaActual(Sorpresa s){
        cartaActual = s;
        return cartaActual;
    }
    
    public ArrayList<Sorpresa> getMazo(){
        return mazo;
    }
    
    public boolean cancelarHipoteca(Casilla casilla){
       boolean puedoCancelar = false;
        Calle calle = (Calle)casilla;
        
        if (calle.soyEdificable()) {
            if (calle.estaHipotecada()) {
                puedoCancelar = jugadorActual == calle.getTitulo().getPropietario();
                
                if (puedoCancelar) {
                    int coste = calle.cancelarHipoteca();
                    jugadorActual.modificarSaldo(coste);
                }
            }
        }
        
        return puedoCancelar;
    }
    
    public boolean comprarTituloPropiedad(Casilla casilla){
        return jugadorActual.comprarTituloPropiedad();
    }
    
    public boolean edificarCasa(Casilla casilla){
        boolean puedoEdificar = false;
        Calle calle =(Calle)casilla;
        
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar = calle.sePuedeEdificarCasa(jugadorActual.getFactorEspeculador());
            
            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);
                
                if(puedoEdificar){
                    int costeEdificarCasa = calle.edificarCasa();
                    jugadorActual.modificarSaldo(-costeEdificarCasa);
                }
            }
        }
        return puedoEdificar;
    }
    
    public boolean edificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        Calle calle = (Calle)casilla;
        
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar = calle.sePuedeEdificarHotel(jugadorActual.getFactorEspeculador());
            
            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarHotel(casilla);
                
                if(puedoEdificar){
                    int costeEdificarHotel = calle.edificarHotel();
                    jugadorActual.modificarSaldo(-costeEdificarHotel);
                }
            }
        }
        return puedoEdificar;
    }
    
    public Sorpresa getCartaActual(){
        return this.cartaActual;
    }
    
    public Jugador getJugadorActual(){
        return this.jugadorActual;
    }
    
    public boolean hipotecarPropiedad(Casilla casilla){
        boolean sePuedeHipotecar, puedoHipotecar = false;
        
        if(casilla.soyEdificable()){
            Calle calle = (Calle)casilla;
            sePuedeHipotecar = !calle.estaHipotecada();
            
            if(sePuedeHipotecar){
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
                
                if(puedoHipotecar){
                    int cantidad = calle.hipotecar();
                    jugadorActual.modificarSaldo(cantidad);
                }
            }
        }
        return puedoHipotecar;   
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres);
        inicializarCartasSorpresa();
        inicializarTablero();  //Tablero
        salidaJugadores();
    }
    
    public boolean intentarSalirCarcel(int metodo){
        boolean libre = false, tengoSaldo;
        int valorDado;
        
            if(metodo == 0){
                Dado dado = GUIQytetet.Dado.getInstance();
                valorDado = dado.nextNumber();
                libre = valorDado > 5;
            }else{
                tengoSaldo = jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
                libre = tengoSaldo;
            }
            
            if(libre)
                jugadorActual.setEncarcelado(false);
            
            return libre;  
    }
    
    public boolean jugar(){
        Dado dado = GUIQytetet.Dado.getInstance();
        int valorDado = dado.nextNumber();
        boolean tienePropietario;
        
        Casilla casillaPosicion=jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = eltablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        tienePropietario = jugadorActual.actualizaPosicion(nuevaCasilla);
        
        if(!nuevaCasilla.soyEdificable()){
            if(nuevaCasilla.getTipo() == TipoCasilla.JUEZ)
                this.encarcelarJugador();
            else if(nuevaCasilla.getTipo() == TipoCasilla.SORPRESA){
                cartaActual = mazo.get(0);
                mazo.remove(0);
            }
        }
        return tienePropietario;
    }
    
    public HashMap<String, Integer> ranking(){
        HashMap<String, Integer> ranking = new HashMap();
        
        for(Jugador j:jugadores){
            int capital = j.obtenerCapital();
            ranking.put(j.getNombre(), capital);
        }
        return ranking; 
    }
    
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){
          ArrayList<Casilla> listaPropiedades = new ArrayList();
          
          for(int i = 0; i< MAX_CASILLAS; i++){
            for(TituloPropiedad t:jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas)){
                  Calle calle = (Calle)(eltablero.obtenerCasillaNumero(i));
                  if (t == calle.getTitulo())
                      listaPropiedades.add(eltablero.obtenerCasillaNumero(i));
            }
          }
          return listaPropiedades;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    public Jugador siguienteJugador(){
        int indice=0;
        
        /*Recorremos el vector de jugadores hasta su maximo*/
        for(int i=0; i<jugadores.size(); i++){
            if(jugadores.get(i)==jugadorActual)
                indice =i;
        }

        jugadorActual = jugadores.get((indice + 1) % jugadores.size());
        return jugadorActual;
    }
    
    public boolean venderPropiedad(Casilla casilla){
        
        
        if (casilla.soyEdificable()) {
            if(jugadorActual.puedoVenderPropiedad(casilla))
                jugadorActual.venderPropiedad(casilla);
            
        }
        return jugadorActual.puedoVenderPropiedad(casilla);
    }
    
    public void encarcelarJugador(){
        Sorpresa carta;
        ArrayList<Sorpresa> ultimaCarta = new ArrayList();
        
        if(!jugadorActual.tengoCartaLibertad()){
            Casilla casillaCarcel = eltablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
        }else{
            carta = jugadorActual.devolverCartaLibertad();
            ultimaCarta.add(carta);
            mazo.addAll(ultimaCarta);
        }
    }
    
    private void inicializarCartasSorpresa(){
        mazo = new ArrayList();
        
        mazo.add(new Sorpresa ("¡Ve a la carcel!", 4, TipoSorpresa.IRACASILLA));
        
        mazo.add(new Sorpresa ("Sal de la carcel con esta carta",0, TipoSorpresa.SALIRCARCEL));
        
        mazo.add(new Sorpresa ("Cobra la cantidad indicada", 300, TipoSorpresa.PAGARCOBRAR));
        
        mazo.add(new Sorpresa ("Pagas la cantidad indicada", -300, TipoSorpresa.PAGARCOBRAR));
        
        mazo.add(new Sorpresa ("Ve a la casilla indicada", 2, TipoSorpresa.IRACASILLA));
        
        mazo.add(new Sorpresa("Ve a la casilla indicada", 7, TipoSorpresa.IRACASILLA));
        
        mazo.add(new Sorpresa ("Cobra la cantidad indicada a todos los jugadores,",100, TipoSorpresa.PORJUGADOR));
        
        mazo.add(new Sorpresa ("Pagas la cantidad indicada a todos los jugadores,",-100, TipoSorpresa.PORJUGADOR));
        
        mazo.add(new Sorpresa ("Pagas la cantidad indicada por las casas y hoteles,",-100, TipoSorpresa.PORCASAHOTEL));

        mazo.add(new Sorpresa ("Cobras la cantidad indicada a todos los jugadores,",100, TipoSorpresa.PORCASAHOTEL));
        
        mazo.add(new Sorpresa ("Felicidades por haberte convertido en un Especulador. disfruta de tus ventajas fiscales y de edificar más que los menos afortunados", 5000, TipoSorpresa.CONVERTIRME));
        
        mazo.add(new Sorpresa("Después de mucho trabajo y mucho esfuerzo, has conseguido convertirte en un Especulador. Disfruta de poder edificar más propiedades y de tus ventajas fiscales.", 3000, TipoSorpresa.CONVERTIRME));
        //Una vez inicializadas, las mezclamos con shuffle
        
        Collections.shuffle(mazo);
    }
    
    private void inicializarJugadores(ArrayList<String> nombres){
        jugadores = new ArrayList();
        
        for (String s: nombres)
            jugadores.add(new Jugador(s));
    }
    
    public Jugador setJugadorActual(Jugador j){
        this.jugadorActual=j;
        return this.jugadorActual;
    }
    
    private void salidaJugadores(){
        for (Jugador j: jugadores){
            j.setCasillaActual(eltablero.obtenerCasillaNumero(0));
        }
        
        Random rand = new Random();
        jugadorActual = jugadores.get(rand.nextInt(jugadores.size()));
    }
    
    @Override
    public String toString(){
        return "Jugadores\n" + jugadores.toString() + "\n\nTablero\n" + eltablero.toString() +
                "\n\nCartas Sorpresa\n" + mazo.toString();
    }
} 

