/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;
import java.util.ArrayList;

public class Jugador {
    private static int FactorEspeculador = 1;
    private boolean encarcelado;
    private String nombre;
    private int saldo = 7500;
    private Casilla casillaActual;
    private ArrayList<TituloPropiedad> misPropiedades= new ArrayList();
    private Sorpresa cartaLibertad;
    
    public Jugador(String nombre){
        this.nombre=nombre;
    }
    
    Jugador(Jugador jugador){
        this.nombre = jugador.getNombre();
        this.saldo=jugador.getSaldo();
        this.encarcelado=jugador.getEncarcelado();
        this.casillaActual=jugador.getCasillaActual();
        this.cartaLibertad=jugador.getCartalibertad();  
        this.misPropiedades=jugador.misPropiedades;
    }
    
    public String getNombre(){
        return this.nombre;
    }
 
    public int getSaldo(){
        return this.saldo;
    }
    
    public Casilla getCasillaActual(){
        return this.casillaActual;
    }
     
    public boolean getEncarcelado(){
         return this.encarcelado;
    }
    
    public ArrayList<TituloPropiedad> getMisPropiedades(){
        return misPropiedades;
    }
    
    public Sorpresa getCartalibertad(){
        return cartaLibertad;
    }
    
    public int getFactorEspeculador(){
        return FactorEspeculador;
    }
    
    void pagarImpuestos(int cantidad){
        this.modificarSaldo(cantidad);
    }
    
    Especulador convertirme(int fianza){
        Especulador convertido = new Especulador(this, fianza);
        
        for(TituloPropiedad t:convertido.getMisPropiedades())
            t.setPropietario(this);
        
        return convertido;
    }
    
    @Override
    public String toString(){
        return "\n{ Jugador: " + nombre + ", saldo: " + saldo + ", encarcelado: " + encarcelado + ", casillaActual: " + casillaActual + "\n misPropiedades: "+ misPropiedades + "}\n"; 
    }
    

     
    public boolean tengoPropiedades(){
        if(misPropiedades.size() > 0)
            return true;
        else 
            return false;
    }
    
    public boolean actualizaPosicion(Casilla nuevaCasilla){
        boolean tengoPropietario = false, encarcelado = false;
        int costeAlquiler;
        
        if(nuevaCasilla.getNumeroCasilla() < casillaActual.getNumeroCasilla())
            this.modificarSaldo(Qytetet.SALDO_SALIDA);
        
        this.setCasillaActual(nuevaCasilla);
            
        if(nuevaCasilla.soyEdificable()){
            Calle calle = (Calle)nuevaCasilla;
            tengoPropietario = calle.tengoPropietario();
            
            if(calle.tengoPropietario()){
                encarcelado = calle.propietarioEncarcelado();
            
                if(!encarcelado){
                    if(!calle.estaHipotecada()){
                        costeAlquiler = calle.cobrarAlquiler();
                        this.modificarSaldo(-costeAlquiler);
                    }
                }
            }
        }else if(nuevaCasilla.getTipo() == TipoCasilla.IMPUESTO){
                int coste = nuevaCasilla.getCoste();
                this.pagarImpuestos(-coste);
            }
        return tengoPropietario;
    }
        
    
    public boolean comprarTituloPropiedad(){
        boolean puedoComprar=false;
        //Si la casilla es edificable, creamos la variable tengoPropietario....
        if(casillaActual.soyEdificable()==true){
            Calle calle = (Calle)casillaActual;
            boolean tengoPropietario=calle.tengoPropietario();
            //Si no tiene propietario guardamos su coste....
            if(!tengoPropietario){
                int costeCompra = calle.getCoste();
                //Si el coste es menor que el saldo del jugador, le asignamos propietario a la casilla
                //la metemos dentro del array de propiedades del jugador....
                //....y le cambiamos el saldo
                //El this significa que soy yo mismo de la clase jugador el que me paso como parametro
                if(costeCompra<=this.saldo){
                    TituloPropiedad titulo=calle.asignarPropietario(this);
                    titulo.setPropietario(this);
                    this.misPropiedades.add(titulo);
                    this.modificarSaldo(-costeCompra);
                    puedoComprar = true;
                }
            }
        }
        return puedoComprar;  
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa cartaDevuelta = cartaLibertad;
        cartaLibertad=null;
        return cartaDevuelta;
    }
    
    void irACarcel(Casilla carcel){
       this.setCasillaActual(carcel);
       this.setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad){
        //EXAMENP4P5
        if(cantidad < 0 && this instanceof Tramposo){
            cantidad = ((Tramposo)this).trampa(cantidad);
        }
        if((saldo+cantidad) > -2000){
            saldo= saldo + cantidad;
        }
    }
    
    int obtenerCapital(){
        int capital=this.saldo;
        /*Recorremos mis propiedades con la variable t y vamos acumulando sus valores que constan
        de: coste + (numCasas + numHoteles)*precioEdficiar luego se lo sumamos al saldo*/
        for (TituloPropiedad t: this.misPropiedades){
            capital += (t.getCasilla().getNumCasas() + t.getCasilla().getNumHoteles()) * t.getPrecioEdificar() + t.getCasilla().getCoste();
        }
        
        return capital;
    }
    
     ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
        ArrayList<TituloPropiedad> resultado = new ArrayList();
        
        for(TituloPropiedad t:misPropiedades){
            //Si el argumento es true, sacame las hipotecadas y si es false, sacame las no hipotecadas
            if(hipotecada==true){
                if(t.getHipotecada()==true)
                    resultado.add(t);
            }else{
                if(t.getHipotecada()==false)
                    resultado.add(t);  
            }    
        }
        return resultado;
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = this.cuantasCasasHotelesTengo();
        this.modificarSaldo(numeroTotal*cantidad);
    }
    
    boolean pagarLibertad(int PrecioLibertad){
        boolean tengoSaldo = this.tengoSaldo(PrecioLibertad);
        
        if(tengoSaldo)
                this.modificarSaldo(-PrecioLibertad);
        
        return tengoSaldo;
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
        boolean esMia = this.esDeMiPropiedad(casilla), tengoSaldo = false;
        Calle calle = (Calle)casilla;
        
        
        if(esMia){
            int costeEdificarCasa = calle.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(-costeEdificarCasa);
        }
        return esMia && tengoSaldo;
    }
    
     boolean puedoEdificarHotel(Casilla casilla){
        boolean tengoSaldo = false;
        boolean esMia = esDeMiPropiedad(casilla);
        
        Calle calle = (Calle)casilla;
        
        if (esMia){
            int costeEdificar = calle.getPrecioEdificar();
            tengoSaldo = tengoSaldo(-costeEdificar);
        }
        
        return esMia && tengoSaldo;
     }
    
    boolean puedoVenderPropiedad(Casilla casilla){
        boolean esMia, hipotecada;
        
        esMia = esDeMiPropiedad(casilla);
        Calle calle = (Calle)casilla;
        hipotecada = calle.estaHipotecada();
        
        return esMia && !hipotecada;
    }
    
    boolean puedoHipotecar(Casilla casilla){
        return esDeMiPropiedad(casilla);
    }
    
    boolean puedoPagarHipoteca(Casilla casilla){
        return true;
    }
    
    void setCartaLibertad(Sorpresa s){
        this.cartaLibertad= new Sorpresa(s.getTexto(), s.getValor(), s.getTipo());
    }
    
    public void setCasillaActual(Casilla casilla){
        this.casillaActual=casilla;
    }
    
    public void setEncarcelado(boolean encarcelado){
        this.encarcelado=encarcelado;
    }
    
    boolean tengoCartaLibertad(){
        if(this.cartaLibertad!=null)
            return true;
        else return false;
    }
    
    void venderPropiedad(Casilla casilla){
        Calle calle = (Calle)casilla;
        
        int precio = calle.venderTitulo();
        modificarSaldo(precio);
        eliminarDeMisPropiedades(casilla);
    }
    
    private int cuantasCasasHotelesTengo(){
        int casas=0, hoteles=0, resultado=0;
 
        for(TituloPropiedad t:misPropiedades){
            if(t.getCasilla().getNumCasas()!=0)
                casas+=t.getCasilla().getNumCasas();
            if(t.getCasilla().getNumHoteles()!=0)
                hoteles+=t.getCasilla().getNumHoteles();
        }
        resultado=casas+hoteles;
        
        return resultado;
        
    }
    
    private void eliminarDeMisPropiedades(Casilla casilla){
        Calle calle = (Calle)casilla;
        TituloPropiedad titulo = calle.getTitulo();
        int i;

        if(this.misPropiedades.contains(titulo)){
            //Con indexOf obtenemos el indice (la posicion del titulo) que nos interesa
            //y se la asignaemos a un entero para luego poder usar remove
            i = this.misPropiedades.indexOf(titulo);
            this.misPropiedades.remove(i);
        }
    }
    
    private boolean esDeMiPropiedad(Casilla casilla){
            /*Metodo contains: si en mispropiedades se encuentra el titulo de la casilla argumento, devuelve true*/
            Calle calle = (Calle)casilla;
            if(misPropiedades.contains(calle.getTitulo()))
                return true;
            else 
                return false;
    }
    
    private boolean tengoSaldo(int cantidad){
        /*Devuelve true si cantidad es menor al saldo del jugador, y false en caso contrario*/
        if(this.saldo>=cantidad)
            return true;
        else 
            return false;
    }

}
