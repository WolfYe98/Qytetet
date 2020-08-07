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
public class TituloPropiedad {
    private String nombre;
    private boolean hipotecada;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private Casilla casilla;
    private Jugador propietario;
    private boolean tengoPropietario;
    
    /*Constructor de la clase, hipotecada es un valor por defecto*/
    TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar){
        this.nombre=nombre;
        this.hipotecada=false;
        this.alquilerBase=alquilerBase;
        this.factorRevalorizacion=factorRevalorizacion;
        this.hipotecaBase=hipotecaBase;
        this.precioEdificar=precioEdificar;
        this.propietario=null;
        this.casilla=null;
        this.tengoPropietario=false;
    }
   
    void setHipotecada(boolean hipotecada){
        this.hipotecada=hipotecada;
    }
    
    void setCasilla(Casilla casilla){
        this.casilla=casilla;
    }
    
    void setPropietario(Jugador propietario){
        this.propietario=propietario;
        if(this.propietario != null){
            this.tengoPropietario = true;
        }
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public Casilla getCasilla(){
        return this.casilla;
    }
    
    public Jugador getPropietario(){
        return this.propietario;
        
    }
    
    boolean getHipotecada(){
        return this.hipotecada;
    }

    int getAlquilerBase() {
        return this.alquilerBase;
    }

    float getFactorRevalorizacion() {
        return this.factorRevalorizacion;
    }

    int getHipotecaBase() {
        return this.hipotecaBase;
    }

    int getPrecioEdificar() {
        return this.precioEdificar;
    }
    
    boolean propietarioEncarcelado(){
        return this.propietario.getEncarcelado();
    }
    
    void cobrarAlquiler(int costeAlquiler){
        this.propietario.modificarSaldo(costeAlquiler);
    }
    
    public boolean tengoPropietario(){
        return this.tengoPropietario;
    }
    
    
    @Override
    public String toString(){
        return "Propiedad{ " + "nombre=" + nombre + ", alquiler base= " + alquilerBase + ", hipotecada= " + hipotecada + "}\n";  
    }
}
