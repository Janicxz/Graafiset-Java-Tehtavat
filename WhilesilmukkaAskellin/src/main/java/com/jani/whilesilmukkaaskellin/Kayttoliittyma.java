/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.whilesilmukkaaskellin;

import javax.swing.JOptionPane;

/**
 *
 * @author Jani
 */
public class Kayttoliittyma extends javax.swing.JFrame {

    /**
     * Creates new form Kayttoliittyma
     */
    public Kayttoliittyma() {
        initComponents();
        jrbtnLaskeva.setActionCommand("Laskeva");
        jrbtnNouseva.setActionCommand("Nouseva");
    }
    
    private boolean tarkistaSyote() {
        int rivit;
        int rivivalit;
        try {
            rivit = Integer.parseInt(jtxtRivit.getText());
            rivivalit = (Integer)jspnRivivalit.getValue();
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, "Rivit tai rivivälit syötetty väärin!\nSyötithän vain numeroita?", "Virhe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (rivit < 10 | rivit > 1000) {
            JOptionPane.showInternalMessageDialog(null, "Rivit syötetty väärin!\nVain lukuja väliltä 10-1000 sallittu.", "Virhe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (rivivalit < 2 || rivivalit > 20) {
            JOptionPane.showInternalMessageDialog(null, "Rivitvälit syötetty väärin!\nVain lukuja väliltä 2-20 sallittu.", "Virhe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtngrpJarjestys = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jspnRivivalit = new javax.swing.JSpinner();
        jtxtRivit = new javax.swing.JTextField();
        Jscrollpane1 = new javax.swing.JScrollPane();
        jtxtareaTulosta = new javax.swing.JTextArea();
        jbtnTulosta = new javax.swing.JButton();
        jbtnTyhjenna = new javax.swing.JButton();
        jrbtnNouseva = new javax.swing.JRadioButton();
        jrbtnLaskeva = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Montako riviä haluat tulostaa:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Rivivälitys:");

        jtxtRivit.setText("100");

        jtxtareaTulosta.setColumns(20);
        jtxtareaTulosta.setRows(5);
        Jscrollpane1.setViewportView(jtxtareaTulosta);

        jbtnTulosta.setText("Tulosta");
        jbtnTulosta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTulostaActionPerformed(evt);
            }
        });

        jbtnTyhjenna.setText("Tyhjennä");
        jbtnTyhjenna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTyhjennaActionPerformed(evt);
            }
        });

        jbtngrpJarjestys.add(jrbtnNouseva);
        jrbtnNouseva.setSelected(true);
        jrbtnNouseva.setText("Nouseva järjestys ..1,2,3..");

        jbtngrpJarjestys.add(jrbtnLaskeva);
        jrbtnLaskeva.setText("Laskeva järjestys ..3,2,1..");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jspnRivivalit)
                    .addComponent(jtxtRivit, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnTulosta, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnTyhjenna, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Jscrollpane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 18, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrbtnNouseva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jrbtnLaskeva)
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtRivit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jspnRivivalit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbtnNouseva)
                    .addComponent(jrbtnLaskeva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(Jscrollpane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnTulosta)
                    .addComponent(jbtnTyhjenna))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnTyhjennaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTyhjennaActionPerformed
        jtxtareaTulosta.setText("");
    }//GEN-LAST:event_jbtnTyhjennaActionPerformed

    private void jbtnTulostaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTulostaActionPerformed
        if (!tarkistaSyote()) {
            return;
        }
        int rivit;
        int rivivalit;
        try {
            rivit = Integer.parseInt(jtxtRivit.getText());
            rivivalit = (Integer)jspnRivivalit.getValue();
        } catch (Exception e) {
            return;
        }
        if (jbtngrpJarjestys.getSelection().getActionCommand().equals("Nouseva")) {
            int i = 1;
            while ( i <= rivit ) {
                jtxtareaTulosta.append(String.format("%d ", i));
                if (i % rivivalit == 0) {
                    jtxtareaTulosta.append("\n");
                }
                i++;
            }   
        }
        else if (jbtngrpJarjestys.getSelection().getActionCommand().equals("Laskeva")) {
            int i = rivit;
            while ( i > 0 ) {
                jtxtareaTulosta.append(String.format("%d ", i));
                if (i % rivivalit == 0) {
                    jtxtareaTulosta.append("\n");
                }
                i--;
            }
        }
    }//GEN-LAST:event_jbtnTulostaActionPerformed

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
    private javax.swing.JScrollPane Jscrollpane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtnTulosta;
    private javax.swing.JButton jbtnTyhjenna;
    private javax.swing.ButtonGroup jbtngrpJarjestys;
    private javax.swing.JRadioButton jrbtnLaskeva;
    private javax.swing.JRadioButton jrbtnNouseva;
    private javax.swing.JSpinner jspnRivivalit;
    private javax.swing.JTextField jtxtRivit;
    private javax.swing.JTextArea jtxtareaTulosta;
    // End of variables declaration//GEN-END:variables
}
