/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.asiakasrekisteri;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 *
 * @author Jani
 */
public class PaaKayttoliittyma extends javax.swing.JFrame {

    /**
     * Creates new form PaaKayttoliittyma
     */
    public PaaKayttoliittyma(String tunnus) {
        initComponents();
        this.setTitle("Tervetuloa: " + tunnus);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMnuBarPaaValikko = new javax.swing.JMenuBar();
        jmnuTiedosto = new javax.swing.JMenu();
        jmnuLopeta = new javax.swing.JMenuItem();
        jmnuHallinta = new javax.swing.JMenu();
        jmnuTuoterekisteri = new javax.swing.JMenuItem();
        jmnuTilaustenhallinta = new javax.swing.JMenuItem();
        jmnuAsiakasrekisteri = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jmnuTiedosto.setText("Tiedosto");

        jmnuLopeta.setText("Lopeta");
        jmnuLopeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuLopetaActionPerformed(evt);
            }
        });
        jmnuTiedosto.add(jmnuLopeta);

        jMnuBarPaaValikko.add(jmnuTiedosto);

        jmnuHallinta.setText("Hallinta");

        jmnuTuoterekisteri.setText("Tuoterekisteri");
        jmnuTuoterekisteri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuTuoterekisteriActionPerformed(evt);
            }
        });
        jmnuHallinta.add(jmnuTuoterekisteri);

        jmnuTilaustenhallinta.setText("Tilaustenhallinta");
        jmnuTilaustenhallinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuTilaustenhallintaActionPerformed(evt);
            }
        });
        jmnuHallinta.add(jmnuTilaustenhallinta);

        jmnuAsiakasrekisteri.setText("Asiakasrekisteri");
        jmnuAsiakasrekisteri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuAsiakasrekisteriActionPerformed(evt);
            }
        });
        jmnuHallinta.add(jmnuAsiakasrekisteri);

        jMnuBarPaaValikko.add(jmnuHallinta);

        setJMenuBar(jMnuBarPaaValikko);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmnuAsiakasrekisteriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuAsiakasrekisteriActionPerformed
        Asiakasrekisterihallinta as = new Asiakasrekisterihallinta();
        as.setVisible(true);
    }//GEN-LAST:event_jmnuAsiakasrekisteriActionPerformed

    private void jmnuLopetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuLopetaActionPerformed
         if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Haluatko varmasti lopettaa?", "",JOptionPane.YES_NO_OPTION))
         {
            WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
         }
    }//GEN-LAST:event_jmnuLopetaActionPerformed

    private void jmnuTilaustenhallintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuTilaustenhallintaActionPerformed
        Tilaustenhallinta th = new Tilaustenhallinta();
        th.setVisible(true);
    }//GEN-LAST:event_jmnuTilaustenhallintaActionPerformed

    private void jmnuTuoterekisteriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuTuoterekisteriActionPerformed
        // TODO add your handling code here:
        Tuoterekisteri tr = new Tuoterekisteri();
        tr.setVisible(true);
    }//GEN-LAST:event_jmnuTuoterekisteriActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaaKayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaaKayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaaKayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaaKayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaaKayttoliittyma("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMnuBarPaaValikko;
    private javax.swing.JMenuItem jmnuAsiakasrekisteri;
    private javax.swing.JMenu jmnuHallinta;
    private javax.swing.JMenuItem jmnuLopeta;
    private javax.swing.JMenu jmnuTiedosto;
    private javax.swing.JMenuItem jmnuTilaustenhallinta;
    private javax.swing.JMenuItem jmnuTuoterekisteri;
    // End of variables declaration//GEN-END:variables
}
