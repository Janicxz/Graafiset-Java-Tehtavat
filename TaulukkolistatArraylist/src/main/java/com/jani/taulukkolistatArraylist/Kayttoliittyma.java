/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.taulukkolistatArraylist;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jani
 */
public class Kayttoliittyma extends javax.swing.JFrame {

    /**
     * Creates new form Kayttoliittyma
     */
    ArrayList<Double> ikaLista;
    public Kayttoliittyma() {
        initComponents();
        jListHenkilot.setModel(new DefaultListModel());
        ikaLista = new ArrayList<Double>();
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
        jtxtNimi = new javax.swing.JTextField();
        jtxtIka = new javax.swing.JTextField();
        jbtnLisaa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListHenkilot = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jtxtKeskiika = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbtnTyhjenna = new javax.swing.JButton();
        jchkTyhjennaKentat = new javax.swing.JCheckBox();
        jbtnPoistaValittu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Nimi:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ikä:");

        jbtnLisaa.setText("Lisää listaan");
        jbtnLisaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLisaaActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListHenkilot);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Keski-ikä:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Anna henkilöiden nimi, ikä ja lisää ne listalle");

        jbtnTyhjenna.setText("Tyhjennä lista");
        jbtnTyhjenna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTyhjennaActionPerformed(evt);
            }
        });

        jchkTyhjennaKentat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jchkTyhjennaKentat.setText("Tyhjennä kentät lisäyksen jälkeen");

        jbtnPoistaValittu.setText("Poista valittu henkilö");
        jbtnPoistaValittu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPoistaValittuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtNimi, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jtxtIka)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtKeskiika, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnPoistaValittu))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnLisaa)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnTyhjenna))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jchkTyhjennaKentat)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtNimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtIka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jchkTyhjennaKentat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnLisaa)
                    .addComponent(jbtnTyhjenna))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jbtnPoistaValittu)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtKeskiika, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean tarkistaSyote() {
        if (jtxtNimi.getText().equals("")) {
            JOptionPane.showInternalMessageDialog(null, "Nimeä ei annettu!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int ika = Integer.valueOf(jtxtIka.getText());
            if (ika <= 0 || ika > 120) {
                JOptionPane.showInternalMessageDialog(null, "Vain ikä väliltä 1-120 hyväksytään!", "Virhe", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, "Ikä on väärin!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    double keskiIka = 0;
    private void paivitaKeskiIka() {
        double summa = 0;
        for (double i : ikaLista) {
            summa += i;
        }
        if (summa != 0) {
            keskiIka = summa / ikaLista.size();
        }
        else {
            keskiIka = 0;
        }
        jtxtKeskiika.setText(String.format("%.2f", keskiIka));
    }
    
    private void jbtnLisaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLisaaActionPerformed
        
        if (!tarkistaSyote()) {
            return;
        }
        DefaultListModel lst =  (DefaultListModel)jListHenkilot.getModel();
        double ika = Double.valueOf(jtxtIka.getText());
        lst.addElement(String.format("%s, ikä: %s", jtxtNimi.getText(), jtxtIka.getText()));
        ikaLista.add(ika);
        paivitaKeskiIka();
        
        if (jchkTyhjennaKentat.isSelected()) {
            jtxtNimi.setText("");
            jtxtIka.setText("");
        }
    }//GEN-LAST:event_jbtnLisaaActionPerformed

    private void jbtnTyhjennaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTyhjennaActionPerformed
        DefaultListModel lst =  (DefaultListModel)jListHenkilot.getModel();
        lst.clear();
        ikaLista.clear();
        paivitaKeskiIka();
    }//GEN-LAST:event_jbtnTyhjennaActionPerformed

    private void jbtnPoistaValittuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPoistaValittuActionPerformed
       int indeksi = jListHenkilot.getSelectedIndex();
       if (indeksi == -1) {
           return;
       }
       DefaultListModel lst =  (DefaultListModel)jListHenkilot.getModel();
       lst.remove(indeksi);
       ikaLista.remove(indeksi);
       paivitaKeskiIka();
    }//GEN-LAST:event_jbtnPoistaValittuActionPerformed

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
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kayttoliittyma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kayttoliittyma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jListHenkilot;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnLisaa;
    private javax.swing.JButton jbtnPoistaValittu;
    private javax.swing.JButton jbtnTyhjenna;
    private javax.swing.JCheckBox jchkTyhjennaKentat;
    private javax.swing.JTextField jtxtIka;
    private javax.swing.JTextField jtxtKeskiika;
    private javax.swing.JTextField jtxtNimi;
    // End of variables declaration//GEN-END:variables
}
