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
public class Calle extends Casilla {
    private TituloPropiedad titulo;
    private int numHoteles = 0;
    private int numCasas = 0;
    
    public Calle(int numcasilla, int coste, TituloPropiedad titulo){
        super(numcasilla, coste);
        setTituloPropiedad(titulo);
        this.titulo.setCasilla(this);
    }
    
    public void setTituloPropiedad(TituloPropiedad titulo){
        this.titulo = titulo;
    }
    
    public int getNumHoteles() {
        return numHoteles;
    }

    public int getNumCasas() {
        return numCasas;
    }
    
    
    public TituloPropiedad getTitulo() {
        return titulo;
    }
    
    public void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }
    
    public void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    
    public int calcularValorHipoteca(){
        int hipotecaBase = this.titulo.getHipotecaBase();
        
        int hipotecaFinal = hipotecaBase + (int)(numCasas * (float)0.5 * hipotecaBase + numHoteles * hipotecaBase);
        
        return hipotecaFinal;
    }
    
    public int cancelarHipoteca(){
        this.titulo.setHipotecada(false);
        int cantidad = (int) (calcularValorHipoteca()*1.1);
        return cantidad;
    }
    
    public int cobrarAlquiler(){
        int costeAlquilerBase = this.titulo.getAlquilerBase();
        int costeAlquiler = (int) (costeAlquilerBase + (this.numCasas*0.5 + this.numHoteles*2));
        this.titulo.cobrarAlquiler(costeAlquiler);
        
        return costeAlquiler;
    }
    
    public int edificarCasa(){
        
       int nuevoNumero = this.numCasas + 1;
       this.setNumCasas(nuevoNumero);
       
       int costeEdificarCasa = this.titulo.getPrecioEdificar();
       return costeEdificarCasa;
    }
    
    public int edificarHotel(){
        
       int nuevoNumero = this.numHoteles + 1;
       this.setNumHoteles(nuevoNumero);
       this.setNumCasas(0);
       
       int costeEdificarHotel = this.titulo.getPrecioEdificar();
       return costeEdificarHotel;
    }
    
    public boolean estaHipotecada(){
        if(this.titulo.getHipotecada()==true)
            return true;
        else return false;
    }
    
    public int hipotecar(){
        this.titulo.setHipotecada(true);
        int cobro = calcularValorHipoteca();
        
        return cobro;
    }
    
    public int getPrecioEdificar(){
        int coste = this.titulo.getPrecioEdificar();
        return coste;
    }
      
    public boolean propietarioEncarcelado(){
        return this.titulo.propietarioEncarcelado();
    }
    
    public boolean sePuedeEdificarCasa(int factor){
        return this.numCasas < 4*factor;
    }
    
    public boolean sePuedeEdificarHotel(int factor){
        return this.numHoteles < (4*factor) && this.numCasas == 4*factor;
    }
    
        
    public TituloPropiedad asignarPropietario(Jugador jugador){
        this.titulo.setPropietario(jugador);
        return this.titulo;
    }
    
    public boolean tengoPropietario(){
        return this.titulo.tengoPropietario();
    }
    
    public int venderTitulo(){
        int precioVenta, precioCompra;
        
        precioCompra = this.coste + (this.numCasas + this.numHoteles) * this.titulo.getPrecioEdificar();
        precioVenta = (int) (precioCompra + this.titulo.getFactorRevalorizacion()*precioCompra);
        
        this.titulo.setPropietario(null);
        setNumHoteles(0);
        setNumCasas(0);
        
        return precioVenta;
        
    }
    
    public void asignarTituloPropiedad() {
        
    }
    
    @Override
    public String toString() {
        return super.toString() + "Calle{" + "titulo=" + titulo + ", Hoteles=" + numHoteles + ", Casas=" + numCasas + "}\n";
    }   
}
