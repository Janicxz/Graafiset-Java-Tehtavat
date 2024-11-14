/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.horoskooppi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author Jani
 */

public class PaaIkkuna extends javax.swing.JFrame {

    /**
     * Creates new form PaaIkkuna
     */
    public PaaIkkuna() {
        initComponents();
        lataaHoroskoopit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldSyntymaPaiva = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxKuukaudet = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabelHoroskooppi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Horoskooppi");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Ole hyvä ja anna syntymäpäiväsi ja valitse syntymäkuukautesi, niin kerron horoskooppisi.");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Syntymäpäivä:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Kuukausi:");

        jComboBoxKuukaudet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tammikuu", "Helmikuu", "Maaliskuu", "Huhtikuu", "Toukokuu", "Kesäkuu", "Heinäkuu", "Elokuu", "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu" }));

        jButton1.setText("Näytä horoskooppi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Horoskooppisi on: ");

        jLabelHoroskooppi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSyntymaPaiva)
                            .addComponent(jComboBoxKuukaudet, 0, 208, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHoroskooppi))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldSyntymaPaiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxKuukaudet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelHoroskooppi))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    List<Horoskooppi> horoskooppiLista;
    private void lataaHoroskoopit() {
        horoskooppiLista = new ArrayList<>();
        horoskooppiLista.add(new Horoskooppi("Vesimies" ,"2020-01-21", "2020-02-20"));
        horoskooppiLista.add(new Horoskooppi("Kalat" ,"2020-02-21", "2020-03-20"));
        horoskooppiLista.add(new Horoskooppi("Oinas" ,"2020-03-21", "2020-04-20"));
        horoskooppiLista.add(new Horoskooppi("Härkä" ,"2020-04-21", "2020-05-21"));
        horoskooppiLista.add(new Horoskooppi("Kaksoset" ,"2020-05-22", "2020-06-21"));
        horoskooppiLista.add(new Horoskooppi("Rapu" ,"2020-06-22", "2020-07-22"));
        horoskooppiLista.add(new Horoskooppi("Leijona" ,"2020-07-23", "2020-08-22"));
        horoskooppiLista.add(new Horoskooppi("Neitsyt" ,"2020-08-23", "2020-09-22"));
        horoskooppiLista.add(new Horoskooppi("Vaaka" ,"2020-09-23", "2020-10-23"));
        horoskooppiLista.add(new Horoskooppi("Skorpioni" ,"2020-10-24", "2020-11-22"));
        horoskooppiLista.add(new Horoskooppi("Jousimies" ,"2020-11-23", "2020-12-21"));
        horoskooppiLista.add(new Horoskooppi("Kauris" ,"2020-12-22", "2020-12-31"));
        // Poikkeustapaus vuodenvaihteen takia
        horoskooppiLista.add(new Horoskooppi("Kauris" ,"2020-01-01", "2020-01-20"));
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int valittuKuukausiInt = (int)jComboBoxKuukaudet.getSelectedIndex()+1;
       int valittuPaiva;

       try {
        valittuPaiva = Integer.parseInt(jTextFieldSyntymaPaiva.getText());

        if (valittuPaiva < 0 || valittuPaiva > 31) {
            JOptionPane.showInternalMessageDialog(null,
            "Vain numeroita väliltä 1-31 syntymäpäivä kenttään kiitos!",
            "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate valittuSyntymaPaiva = LocalDate.of(2020, valittuKuukausiInt, valittuPaiva);
        for (Horoskooppi hk : horoskooppiLista) {
            if (hk.onkoHoroskooppi(valittuSyntymaPaiva)) {
                jLabelHoroskooppi.setText(hk.getNimi());
            }
        }
       } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showInternalMessageDialog(null, "Vain numeroita syntymäpäivä kenttään kiitos!", "Virhe", JOptionPane.ERROR_MESSAGE);
       }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(PaaIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaaIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaaIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaaIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaaIkkuna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxKuukaudet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelHoroskooppi;
    private javax.swing.JTextField jTextFieldSyntymaPaiva;
    // End of variables declaration//GEN-END:variables
}
