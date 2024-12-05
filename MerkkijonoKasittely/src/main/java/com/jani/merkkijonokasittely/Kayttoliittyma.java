/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.merkkijonokasittely;

import java.util.ArrayList;

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
        jrdTeksti1Korvaa.setActionCommand("korvaaVaikea");
        jrdTeksti1AutoTaulukko.setActionCommand("AutoTaulukko");
        jrdTeksti1AlkaakoKkirjaimella.setActionCommand("AlkaakoKkirjaimella");
        jrdTeksti1PieneksiLiita.setActionCommand("Teksti1Pieneksiliita");
        jrdTeksti2Suureksi.setActionCommand("teksti2suureksi");
        jrdTeksti1Pituus.setActionCommand("teksti1pituus");
        jrdTeksti1Yhtasuuri.setActionCommand("teksti1yhtasuuri");
        jrdTeksti1OnkoKissa.setActionCommand("onkokissa");
        jrdTeksti1Sanojenmaara.setActionCommand("sanojenmaara");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpMjono = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtTeksti1 = new javax.swing.JTextField();
        jtxTeksti2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtTulos = new javax.swing.JTextArea();
        jrdTeksti1Korvaa = new javax.swing.JRadioButton();
        jbtnAja = new javax.swing.JButton();
        jrdTeksti1AutoTaulukko = new javax.swing.JRadioButton();
        jrdTeksti1AlkaakoKkirjaimella = new javax.swing.JRadioButton();
        jrdTeksti1Yhtasuuri = new javax.swing.JRadioButton();
        jrdTeksti1Pituus = new javax.swing.JRadioButton();
        jrdTeksti2Suureksi = new javax.swing.JRadioButton();
        jrdTeksti1PieneksiLiita = new javax.swing.JRadioButton();
        jrdTeksti1OnkoKissa = new javax.swing.JRadioButton();
        jrdTeksti1Sanojenmaara = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Merkkijonon käsittelyharjoituksia");

        jLabel2.setText("Teksti1:");

        jLabel3.setText("Teksti2:");

        jLabel4.setText("Tulos:");

        jtxtTulos.setColumns(20);
        jtxtTulos.setRows(5);
        jScrollPane1.setViewportView(jtxtTulos);

        btngrpMjono.add(jrdTeksti1Korvaa);
        jrdTeksti1Korvaa.setText("Teksti1: korvaa \"vaikea\" sanalla \"helppo\"");

        jbtnAja.setText("Aja");
        jbtnAja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAjaActionPerformed(evt);
            }
        });

        btngrpMjono.add(jrdTeksti1AutoTaulukko);
        jrdTeksti1AutoTaulukko.setText("Teksti1: Kirjoita Opel, Volvo, BMW, Mersu ja ohjelma muuntaa mjonon string taulukoksi ja taulukko tulostetaan takaisin for-silmukalla");

        btngrpMjono.add(jrdTeksti1AlkaakoKkirjaimella);
        jrdTeksti1AlkaakoKkirjaimella.setText("Teksti1: Alkaako K kirjaimella?");

        btngrpMjono.add(jrdTeksti1Yhtasuuri);
        jrdTeksti1Yhtasuuri.setText("Teksti1 ja Teksti2 yhtäsuuruuden vertaaminen");

        btngrpMjono.add(jrdTeksti1Pituus);
        jrdTeksti1Pituus.setText("Teksti1 pituus");

        btngrpMjono.add(jrdTeksti2Suureksi);
        jrdTeksti2Suureksi.setText("Teksti2 SUUREKSI");

        btngrpMjono.add(jrdTeksti1PieneksiLiita);
        jrdTeksti1PieneksiLiita.setText("Teksti1 pieneksi ja Teksti2 liitetään perään");

        btngrpMjono.add(jrdTeksti1OnkoKissa);
        jrdTeksti1OnkoKissa.setText("Teksti1: Löytyykö teksti kissa");

        btngrpMjono.add(jrdTeksti1Sanojenmaara);
        jrdTeksti1Sanojenmaara.setText("Teksti1: Sanojen määrä");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrdTeksti1AutoTaulukko)
                            .addComponent(jrdTeksti1Korvaa)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrdTeksti1Yhtasuuri)
                                    .addComponent(jrdTeksti1Pituus)
                                    .addComponent(jrdTeksti2Suureksi)
                                    .addComponent(jrdTeksti1PieneksiLiita)
                                    .addComponent(jrdTeksti1AlkaakoKkirjaimella))
                                .addGap(168, 168, 168)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jrdTeksti1OnkoKissa)
                            .addComponent(jrdTeksti1Sanojenmaara)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(38, 38, 38)
                                .addComponent(jtxTeksti2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(38, 38, 38)
                                .addComponent(jtxtTeksti1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnAja)
                .addGap(308, 308, 308))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtTeksti1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtxTeksti2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrdTeksti1Yhtasuuri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrdTeksti1Pituus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrdTeksti2Suureksi))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrdTeksti1PieneksiLiita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrdTeksti1AlkaakoKkirjaimella)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrdTeksti1Korvaa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrdTeksti1AutoTaulukko)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrdTeksti1OnkoKissa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrdTeksti1Sanojenmaara)
                .addGap(26, 26, 26)
                .addComponent(jbtnAja)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAjaActionPerformed
        String komento = btngrpMjono.getSelection().getActionCommand();
        String teksti = "";
        jtxtTulos.setText("");
        switch (komento) {
            case "korvaaVaikea":
                teksti = jtxtTeksti1.getText();
                teksti = teksti.replace("vaikea", "helppo");
                jtxtTulos.setText(teksti);
                break;
            case "AutoTaulukko":
                String[] autot = jtxtTeksti1.getText().replace(" ", "").split(",");
                for (int i = 0; i < autot.length; i++) {
                    if (i == autot.length-1) {
                        teksti += autot[i];
                    }
                    else {
                        teksti += autot[i] + ", ";
                    }
                }
                jtxtTulos.append(teksti);
                break;
            case "AlkaakoKkirjaimella":
                if (jtxtTeksti1.getText().toUpperCase().startsWith("K")) {
                    jtxtTulos.setText("Teksti1 alkaa K kirjaimella.");
                }
                else {
                    jtxtTulos.setText("Teksti1 ei ala K kirjaimella.");
                }
                break;
            case "Teksti1Pieneksiliita":
                teksti = jtxtTeksti1.getText().toLowerCase();
                teksti += " " + jtxTeksti2.getText();
                jtxtTulos.setText(teksti);
                break;
            case "teksti2suureksi":
                teksti = jtxTeksti2.getText().toUpperCase();
                jtxtTulos.setText(teksti);
                break;
            case "teksti1pituus":
                teksti = String.format("Teksti1 pituus on %d merkkiä", jtxtTeksti1.getText().length());
                jtxtTulos.setText(teksti);
                break;
            case "teksti1yhtasuuri":
                if (jtxtTeksti1.getText().length() == jtxTeksti2.getText().length()) {
                    jtxtTulos.setText("Teksti 1 ja Teksti2 ovat yhtäsuuria.");
                }
                else {
                    jtxtTulos.setText("Teksti 1 ja Teksti2 eivät ole yhtäsuuria.");
                }
                break;
            case "onkokissa":
                if (jtxtTeksti1.getText().toLowerCase().contains("kissa")) {
                    jtxtTulos.setText("Teksti1stä löytyy kissa.");
                }
                else {
                    jtxtTulos.setText("Teksti1stä ei löydy kissaa.");
                }
                break;
            case "sanojenmaara":
                String[] sanat = jtxtTeksti1.getText().split(" ");
                ArrayList<String> sanaLista = new ArrayList<String>();
                for (String sana: sanat) {
                    if (!sana.equals("")) {
                        sanaLista.add(sana);
                    }
                }
                int sanaMaara = sanaLista.size();
                jtxtTulos.setText(String.format("Teksti1 sanojen määrä on %d", sanaMaara));
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jbtnAjaActionPerformed

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
    private javax.swing.ButtonGroup btngrpMjono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAja;
    private javax.swing.JRadioButton jrdTeksti1AlkaakoKkirjaimella;
    private javax.swing.JRadioButton jrdTeksti1AutoTaulukko;
    private javax.swing.JRadioButton jrdTeksti1Korvaa;
    private javax.swing.JRadioButton jrdTeksti1OnkoKissa;
    private javax.swing.JRadioButton jrdTeksti1PieneksiLiita;
    private javax.swing.JRadioButton jrdTeksti1Pituus;
    private javax.swing.JRadioButton jrdTeksti1Sanojenmaara;
    private javax.swing.JRadioButton jrdTeksti1Yhtasuuri;
    private javax.swing.JRadioButton jrdTeksti2Suureksi;
    private javax.swing.JTextField jtxTeksti2;
    private javax.swing.JTextField jtxtTeksti1;
    private javax.swing.JTextArea jtxtTulos;
    // End of variables declaration//GEN-END:variables
}
