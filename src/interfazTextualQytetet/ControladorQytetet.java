/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazTextualQytetet;
import modeloqytetet.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Sandro
 */
public class ControladorQytetet {
    private Qytetet juego;
    private Jugador jugador;
    private Casilla casillaActual;
    private VistaTextualQytetet vista;
    
    ControladorQytetet(){
        this.vista = new VistaTextualQytetet();
    }
    
    void inicializacionJuego(){
        juego = Qytetet.getInstance();
        ArrayList<String> nombres = new ArrayList();
        nombres = vista.obtenerNombreJugadores();
        juego.inicializarJuego(nombres);
        jugador = juego.getJugadorActual();
        casillaActual = jugador.getCasillaActual();
        System.out.println(juego.toString());
    }
    
    void desarrolloJuego(){
        boolean fin = false;
        boolean bancarrota = jugador.getSaldo() <=0;
        int opcion2 = -1;
      
   
        vista.mostrar("\n-----------------------------QUE COMIENCE EL JUEGO---------------------------------------");
        while(!fin){
            vista.mostrar("->Comienza el turno del jugador: " + jugador.toString());

            boolean libre = !(jugador.getEncarcelado());

            if(!libre){
                vista.mostrar("El jugador " + jugador.getNombre() + " está encarcelado");
                
                int opcion = vista.menuSalirCarcel();
                libre = juego.intentarSalirCarcel(opcion);

                if(libre)
                    vista.mostrar("has salido de la carcel ");
                else
                    vista.mostrar("no has salido de la carcel ");
            }
            
           if(libre){
               
               boolean noTienePropietario;
               noTienePropietario = !(juego.jugar());
               actualizarCasilla();
               System.out.println("\n---------------------------------------");
               vista.mostrar(jugador.getNombre() + " \nse desplaza hasta la casilla numero " + casillaActual.getNumeroCasilla());
               System.out.println("\n---------------------------------------");
               vista.mostrar("\nInformación de la casilla:" + casillaActual);
               System.out.println("\n---------------------------------------");
               vista.mostrar("El saldo actual del jugador " +jugador.getNombre() + " es de " + jugador.getSaldo());
               
               if(!bancarrota){
                   if(!jugador.getEncarcelado()){
                       if(casillaActual.getTipo() == TipoCasilla.CALLE){
                           if(noTienePropietario){
                                int saldo = jugador.getSaldo();
                                System.out.println("\n---------------------------------------");
                                vista.mostrar("El saldo disponible es de: " + saldo);
                                vista.mostrar("El precio de la propiedad es de: " +casillaActual.getCoste());
                                vista.mostrar("El saldo final quedaria " +(saldo-casillaActual.getCoste()));
                                System.out.println("\n---------------------------------------");
                                boolean quieroComprar = vista.elegirQuieroComprar();
                                
                                if(quieroComprar){
                                    boolean comprado = juego.comprarTituloPropiedad(casillaActual);
                                    vista.mostrar("El saldo actual del jugador " +jugador.getNombre() + " es de " + jugador.getSaldo());
                                    
                                }
                           }
                       }
                       else if(casillaActual.getTipo() == TipoCasilla.SORPRESA){
                           noTienePropietario = juego.aplicarSorpresa();
                           vista.mostrar("Carta sorpresa : " + juego.getCartaActual().getTexto() );
                           
                           if(!jugador.getEncarcelado()){
                               if(!bancarrota){
                                   if(casillaActual.getTipo() == TipoCasilla.CALLE){
                                       if(noTienePropietario){
                                           int saldo = jugador.getSaldo();
                                           System.out.println("\n---------------------------------------");
                                           
                                           vista.mostrar("El saldo disponible es de: " + saldo);
                                           vista.mostrar("El precio de la propiedad es de: " +casillaActual.getCoste());
                                           vista.mostrar("el saldo final seria de " +(saldo-casillaActual.getCoste()));
                                           System.out.println("\n---------------------------------------");
                                           boolean quieroComprar = vista.elegirQuieroComprar();
                                           
                                           if(quieroComprar){
                                                boolean comprado = juego.comprarTituloPropiedad(casillaActual);
                                                System.out.println("\n---------------------------------------");
                                                vista.mostrar("El saldo actual del jugador " +jugador.getNombre() + " es de " + jugador.getSaldo());
                                                System.out.println("\n---------------------------------------");
                                            }
                                       }
                                   }
                               }
                           }
                       }
                       
                       if(!jugador.getEncarcelado() && !bancarrota && jugador.tengoPropiedades()){
                           System.out.println("\n---------------------------------------");
                           ArrayList<TituloPropiedad> listaPropiedades = jugador.getMisPropiedades();
                           ArrayList<Casilla> casillas = new ArrayList();
                           ArrayList<String> nombres = new ArrayList();
                           
                           for( TituloPropiedad t : listaPropiedades){
                                   Casilla c = t.getCasilla();
                                   casillas.add(c);
                                   nombres.add(t.getNombre());
                               
                           }
                           
                           boolean correcto;
                           System.out.println("\n---------------------------------------");
                           while(opcion2!= 0){
                               if(casillas.size() == 0){
                                   System.out.println("No tienes propiedades");
                                   break;
                               }
                               else{
                                   vista.mostrar("Elige propiedad\n");
                                   Casilla metodo = elegirPropiedad(casillas);

                                   System.out.println("\n---------------------------------------");
                                   vista.mostrar("\nCasilla actual" + metodo);
                                   opcion2 = vista.menuGestionInmobiliaria();
                                   System.out.println("\n---------------------------------------");
                                   if(opcion2 == 1){
                                       vista.mostrar("saldo previo: " + jugador.getSaldo());
                                       correcto = juego.edificarCasa(metodo);
                                       vista.mostrar("saldo posterior: " + jugador.getSaldo());
                                       if(correcto){
                                          vista.mostrar("se ha edificado la casa en" + metodo);
                                       }
                                   }
                                   if(opcion2== 2){
                                        vista.mostrar("saldo previo: " + jugador.getSaldo());                                  

                                       correcto = juego.edificarHotel(metodo);
                                       vista.mostrar("saldo posterior: " + jugador.getSaldo());
                                       if(correcto){
                                          vista.mostrar("se ha edificado la hotel en" + metodo);
                                       }
                                   }                               
                                   if(opcion2 == 3){
                                       vista.mostrar("saldo previo: " + jugador.getSaldo());
                                       correcto = juego.venderPropiedad(metodo);
                                       vista.mostrar("saldo posterior: " + jugador.getSaldo());
                                       if(correcto){
                                          vista.mostrar("se ha vendido la propiedad de " + metodo);
                                          casillas.remove(metodo);
                                       }
                                   }
                                   if(opcion2 == 4){
                                       vista.mostrar("saldo previo: " + jugador.getSaldo());
                                      correcto = juego.hipotecarPropiedad(metodo);
                                      vista.mostrar("saldo posterior: " + jugador.getSaldo());
                                      if(correcto){
                                          vista.mostrar("se ha hipotecado la casilla " + metodo);
                                       }
                                   }
                                   if(opcion2 == 5){
                                       vista.mostrar("saldo previo: " + jugador.getSaldo());
                                       correcto= juego.cancelarHipoteca(metodo);
                                       vista.mostrar("saldo posterior: " + jugador.getSaldo());
                                       if(correcto){
                                          vista.mostrar("se ha cancelado la hipoteca " + metodo);
                                       }
                                   }
                               } 
                           }
                       }
                    }
                }
            }
            if(!bancarrota){
                juego.siguienteJugador();
                jugador = juego.getJugadorActual();
                System.out.println("\n---------------------------------------");
                vista.mostrar("Siguente jugador -> Nombre: "+ jugador.getNombre());
                System.out.println("\n---------------------------------------");
            }
            if(bancarrota){
                System.out.println("\n---------------------------------------");
                System.out.println("\n---------------------------------------");
                juego.ranking();
                fin = true;
            }
        }
   }
    
    private void actualizarCasilla() {
        casillaActual = jugador.getCasillaActual();
    }

    
    public Casilla elegirPropiedad(ArrayList<Casilla> propiedades){ 
      //este metodo toma una lista de propiedades y genera una lista de strings, con el numero y nombre de las propiedades
      //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion;
        
       ArrayList<String> listaPropiedades= new ArrayList();
        for ( Casilla casilla: propiedades) {
            Calle calle = (Calle)casilla;
            listaPropiedades.add( "\t"+casilla.getNumeroCasilla()+"\t"+calle.getTitulo().getNombre()); 
        }
        seleccion=vista.menuElegirPropiedad(listaPropiedades);  
        return propiedades.get(seleccion);
    }

    
public static void main(String[] args){
        ControladorQytetet controlador = new ControladorQytetet();
        controlador.inicializacionJuego();
        controlador.desarrolloJuego();
    }
}
    
