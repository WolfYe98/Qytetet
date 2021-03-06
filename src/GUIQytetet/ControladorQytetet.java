/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modeloqytetet.Calle;
import modeloqytetet.Jugador;
import modeloqytetet.Qytetet;
import modeloqytetet.TipoCasilla;

/**
 *
 * @author Sandro
 */
public class ControladorQytetet extends javax.swing.JFrame {

    /**
     * Creates new form ControladorQytetet
     */
    public ControladorQytetet() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        vistaQytetet2 = new GUIQytetet.VistaQytetet();
        jbSalirCarcelPagando = new javax.swing.JButton();
        jbSalirCarcelDado = new javax.swing.JButton();
        jbJugar = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jbSiguienteJugador = new javax.swing.JButton();
        jbAplicarSorpresa = new javax.swing.JButton();
        jbEdificarCasa = new javax.swing.JButton();

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QYTETET");
        setAutoRequestFocus(false);
        setResizable(false);

        jbSalirCarcelPagando.setText("Intentar salir carcel pagando");
        jbSalirCarcelPagando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelPagandoActionPerformed(evt);
            }
        });

        jbSalirCarcelDado.setText("Intentar salir carcel tirando dado");
        jbSalirCarcelDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelDadoActionPerformed(evt);
            }
        });

        jbJugar.setText("Jugar");
        jbJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbJugarActionPerformed(evt);
            }
        });

        jbComprar.setText("Comprar");
        jbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbComprarActionPerformed(evt);
            }
        });

        jbSiguienteJugador.setText("Pasar turno");
        jbSiguienteJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteJugadorActionPerformed(evt);
            }
        });

        jbAplicarSorpresa.setText("Aplicar Sorpresa");
        jbAplicarSorpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAplicarSorpresaActionPerformed(evt);
            }
        });

        jbEdificarCasa.setText("Edificar Aqui");
        jbEdificarCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEdificarCasaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(vistaQytetet2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbSalirCarcelPagando)
                                    .addComponent(jbSalirCarcelDado, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbSiguienteJugador)
                                    .addComponent(jbComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jbEdificarCasa))
                            .addComponent(jbAplicarSorpresa))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vistaQytetet2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbSalirCarcelPagando)
                            .addComponent(jbComprar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbSiguienteJugador)
                            .addComponent(jbSalirCarcelDado)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbAplicarSorpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbJugar)
                    .addComponent(jbEdificarCasa))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSiguienteJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteJugadorActionPerformed
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        this.jbAplicarSorpresa.setEnabled(false);
        modeloQytetet.siguienteJugador();
        
        if(modeloQytetet.getJugadorActual().getEncarcelado()){
            this.jbSalirCarcelPagando.setEnabled(true);
            this.jbSalirCarcelDado.setEnabled(true);
        }
        else{
            this.jbJugar.setEnabled(true);
        }
        this.vistaQytetet2.Actualizar(modeloQytetet);
    }//GEN-LAST:event_jbSiguienteJugadorActionPerformed

    private void jbAplicarSorpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAplicarSorpresaActionPerformed
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbComprar.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        
        modeloQytetet.setCartaActual(modeloQytetet.getMazo().get(0));
        boolean propietariado = modeloQytetet.aplicarSorpresa();
        this.vistaQytetet2.Actualizar(modeloQytetet);
        if(!modeloQytetet.getJugadorActual().getEncarcelado()){
            if(!propietariado && modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == TipoCasilla.CALLE){
                this.jbComprar.setEnabled(true);
            }
        }
        else{
            this.jbSalirCarcelDado.setEnabled(true);
            this.jbSalirCarcelPagando.setEnabled(true);
        }
        this.jbSiguienteJugador.setEnabled(true);
    }//GEN-LAST:event_jbAplicarSorpresaActionPerformed

    private void jbSalirCarcelPagandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelPagandoActionPerformed
        boolean resultado = modeloQytetet.intentarSalirCarcel(1);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);

            if(resultado){
               JOptionPane.showMessageDialog(this, "Sales de la cárcel");
               this.jbJugar.setEnabled(true);
            }else {
               JOptionPane.showMessageDialog(this, "NO sales de la carcel");
               this.jbSiguienteJugador.setEnabled(true);       
             }

            this.vistaQytetet2.Actualizar(modeloQytetet);
         
    }//GEN-LAST:event_jbSalirCarcelPagandoActionPerformed

    private void jbSalirCarcelDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelDadoActionPerformed
        boolean resultado = modeloQytetet.intentarSalirCarcel(0);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);

            if(resultado){
               JOptionPane.showMessageDialog(this, "Sales de la cárcel");
               this.jbJugar.setEnabled(true);
            }else {
               JOptionPane.showMessageDialog(this, "NO sales de la carcel");
               this.jbSiguienteJugador.setEnabled(true);       
             }

            this.vistaQytetet2.Actualizar(modeloQytetet);
    }//GEN-LAST:event_jbSalirCarcelDadoActionPerformed

    private void jbJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbJugarActionPerformed
        boolean propietario = modeloQytetet.jugar();
        boolean fin = false;
        this.jbJugar.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        jugadores = modeloQytetet.getJugadores();
        for(Jugador j : jugadores){
            if(j.getSaldo() <= 0){
                fin = true;
            }
        }
        if(!fin){
            if(modeloQytetet.getJugadorActual().getEncarcelado() == true){
                if(modeloQytetet.getJugadorActual().getSaldo() > 200){
                    this.jbSalirCarcelPagando.setEnabled(true);
                }
                this.jbSalirCarcelDado.setEnabled(true);
            }
            else if(modeloQytetet.getJugadorActual().getCasillaActual().getTipo() != TipoCasilla.CALLE){
                this.jbComprar.setEnabled(false);   
            }
            else if(!propietario){
                this.jbComprar.setEnabled(true);
            }
            if(modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == TipoCasilla.SORPRESA){
                this.jbAplicarSorpresa.setEnabled(true);
            }
            this.jbSiguienteJugador.setEnabled(true);
            vistaQytetet2.Actualizar(modeloQytetet);
        }
        else{
            this.jbSalirCarcelPagando.setEnabled(false);
            this.jbSalirCarcelDado.setEnabled(false);
            this.jbSiguienteJugador.setEnabled(false);
            this.jbAplicarSorpresa.setEnabled(false);
            this.jbJugar.setEnabled(false);
            this.jbComprar.setEnabled(false);
            
            HashMap<String, Integer> ranking = new HashMap();
            ranking = modeloQytetet.ranking();
            Ranking ventanaRanking = new Ranking(this, true, ranking);
            ventanaRanking.setVisible(true);
        }
    }//GEN-LAST:event_jbJugarActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        boolean comprado = modeloQytetet.getJugadorActual().comprarTituloPropiedad();
        this.jbSalirCarcelDado.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbComprar.setEnabled(false);
        this.jbJugar.setEnabled(false);
        if(comprado){
            JOptionPane.showMessageDialog(this, modeloQytetet.getJugadorActual().getNombre() + " ha comprado esta casilla");
        }
        else{
            JOptionPane.showMessageDialog(this, modeloQytetet.getJugadorActual().getNombre() + " no ha comprado esta casilla");
        }
        this.jbSiguienteJugador.setEnabled(true);
        vistaQytetet2.Actualizar(modeloQytetet);
    }//GEN-LAST:event_jbComprarActionPerformed

    private void jbEdificarCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEdificarCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbEdificarCasaActionPerformed
    private void habilitarComienzoTurno(){
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);        
        this.jbJugar.setEnabled(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ControladorQytetet controladorQytetet = new ControladorQytetet();
        CapturaNombreJugadores capturaNombres= new CapturaNombreJugadores(controladorQytetet, true);  
        ArrayList<String> nombres= capturaNombres.obtenerNombres();
        modeloQytetet.inicializarJuego(nombres);
        Dado.createInstance(controladorQytetet);
        controladorQytetet.actualizar(modeloQytetet);
        controladorQytetet.setVisible(true);
    }
    private void actualizar(Qytetet q){
       this.habilitarComienzoTurno();
       vistaQytetet2.Actualizar(q);
    }
    private static Qytetet modeloQytetet = Qytetet.getInstance();
    private static ArrayList<Jugador> jugadores = new ArrayList();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jbAplicarSorpresa;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbEdificarCasa;
    private javax.swing.JButton jbJugar;
    private javax.swing.JButton jbSalirCarcelDado;
    private javax.swing.JButton jbSalirCarcelPagando;
    private javax.swing.JButton jbSiguienteJugador;
    private GUIQytetet.VistaQytetet vistaQytetet2;
    // End of variables declaration//GEN-END:variables
}
