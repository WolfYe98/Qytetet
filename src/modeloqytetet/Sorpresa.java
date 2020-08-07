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
public class Sorpresa {
    
    private String texto;
    private TipoSorpresa tipo;
    private int valor=0;
    
    Sorpresa(String texto,int valor, TipoSorpresa tipo){   //Constructor de la clase
        this.texto=texto;
        this.valor=valor;
        this.tipo=tipo;
    }
    
    public String getTexto(){
        return this.texto;
    }
    
    TipoSorpresa getTipo(){
        return this.tipo;
    }
    
    int getValor(){
        return this.valor;
    }
    @Override
    public String toString() {
        return "Sorpresa{" + "texto=" + texto + ", valor=" +
        Integer.toString(valor) + ", tipo=" + tipo + "}\n";
    }
}
