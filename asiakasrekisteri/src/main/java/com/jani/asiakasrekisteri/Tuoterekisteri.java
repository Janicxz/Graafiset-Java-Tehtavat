/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.asiakasrekisteri;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.mariadb.jdbc.Connection;

import com.jani.asiakasrekisteri.Tilaustenhallinta.Tilaus;
import com.jani.asiakasrekisteri.Tilaustenhallinta.TilausRivi;

import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Jani
 */
public class Tuoterekisteri extends javax.swing.JFrame {
    /**
     * Sisältää tuotteeseen liittyvän datan
     */
    class Tuote {
        private int TUOTENUMERO;
        private String TUOTENIMI;
        private int AHINTA;

        public int getTUOTENUMERO() {
            return TUOTENUMERO;
        }
        public String getTUOTENIMI() {
            return TUOTENIMI;
        }
        public int getAHINTA() {
            return AHINTA;
        }
        public Tuote(int tuotenro, String nimi, int hinta) {
            this.TUOTENUMERO = tuotenro;
            this.TUOTENIMI = nimi;
            this.AHINTA = hinta;
        }
    }
    /**
     * Lista tietokannasta haetuista tuotteista
     */
    class TuoteLista {
        private ArrayList<Tuote> TUOTTEET;

        public ArrayList<Tuote> getTUOTTEET() {
            return TUOTTEET;
        }

        public TuoteLista () {
            TUOTTEET = new ArrayList<>();
            lataaUusiTuotelista();
        }
        /**
         * Lisää uusi tuote tietokantaan ja päivittää taulukon
         * @param nimi
         * @param ahinta
         */
        public void lisaaTuote(String nimi, int ahinta) {
            String query = "INSERT INTO tuote(`TUOTENIMI`, `AHINTA`) VALUES(?, ?)";
            PreparedStatement st;

            try {
                Connection yhteys  = luoYhteys();
                st = yhteys.prepareStatement(query);
                st.setString(1, nimi);
                st.setInt(2, ahinta);
                
                suoritaTurvallinenSQLKysely(st, yhteys, "lisätty", true);
            } catch (SQLException e) {
                System.out.println("Virhe tuotteen lisäämisessä!");
                e.printStackTrace();
            }
        }
        /**
         * Päivittää tuotteen tiedot tietokannassa ja päivittää taulukon
         * @param tuoteNumero
         * @param nimi
         * @param ahinta
         */
        public void paivitaTuote(int tuoteNumero, String nimi, int ahinta) {
            String query = "UPDATE `tuote` SET TUOTENIMI=?, AHINTA=? WHERE TUOTENUMERO=?";
            PreparedStatement st;

            try {
                Connection yhteys  = luoYhteys();
                st = yhteys.prepareStatement(query);
                st.setString(1, nimi);
                st.setInt(2, ahinta);
                st.setInt(3, tuoteNumero);
                
                suoritaTurvallinenSQLKysely(st, yhteys, "päivitetty", true);
            } catch (SQLException e) {
                System.out.println("Virhe tuotteen päivittämisessä!");
                e.printStackTrace();
            }
        }
        /**
         * Poistaa tuotteen tietokannasta ja taulukosta
         * @param tuoteNumero
         */
        public void poistaTuote(int tuoteNumero) {
            String query = "DELETE FROM `tuote` WHERE TUOTENUMERO=?";
            PreparedStatement st;
            try {
                Connection yhteys  = luoYhteys();
                st = yhteys.prepareStatement(query);
                st.setInt(1, tuoteNumero);
                
                suoritaTurvallinenSQLKysely(st, yhteys, "poistettu", true);
            } catch (SQLException e) {
                System.out.println("Virhe tuotteen poistamisessa!");
                e.printStackTrace();
            }
        }
        /**
         * Lataa uuden tuotelistan tietokannasta
         */
        public void lataaUusiTuotelista() {
            String query = "SELECT TUOTENUMERO, TUOTENIMI, AHINTA FROM `tuote` ";
            Statement st;
            ResultSet rs;
            this.TUOTTEET.clear();

            try {
                Connection yhteys  = luoYhteys();
                st = yhteys.createStatement();
                rs = st.executeQuery(query);

                Tuote tuote;
                while (rs.next()) {
                    tuote = new Tuote(rs.getInt("TUOTENUMERO"),
                    rs.getString("TUOTENIMI"), rs.getInt("AHINTA"));
                    this.TUOTTEET.add(tuote);
                }
                yhteys.close();
            } catch (SQLException e) {
                System.out.println("Virhe tuotteiden haussa!");
                e.printStackTrace();
            }
        }
    }
    /**
     * Suorittaa SQL kyselyn turvallisesti PreparedStatement avulla, näyttää käyttäjälle annetun viestin lopuksi
     * @param kysely Suoritettava kysely
     * @param viesti Käyttäjälle näytettävä viesti
     */
    public void suoritaTurvallinenSQLKysely(PreparedStatement kysely, Connection yhteys, String viesti, boolean suljeYhteys) {
        try {
            int tulos = kysely.executeUpdate();

            if (tulos > 0) {
                paivitaTuotteetTaulukko();
                if (suljeYhteys) {
                    JOptionPane.showMessageDialog(null, "Data " + viesti + " onnistuneesti");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Data ei " + viesti);
            }
            if (suljeYhteys) {
                yhteys.close();
            }
        } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Data ei " + viesti);
        }
    }
    /**
     * Luo uusi MySQL yhteys asiakastietokantaan ja palauta Connection
     * @return Palauttaa asiakastietokantaan yhdistetyn Connection olion, jos yhteyden muodostaminen epäonnistui palauttaa null.
     */
    public Connection luoYhteys() {
        Connection cn = null;
        try {
            String kayttaja = "kehittaja";
            cn = (Connection) DriverManager.getConnection("jdbc:mariadb://" + "localhost" + ":3306/ASIAKASTILAUSJARJESTELMA" + "?socketTimeout=2000", kayttaja, "SalaSana123!");
            return cn;
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Yhteyden muodostaminen asiakastietokantaan epäonnistui!");
            System.out.println("Yhteyden luominen epäonnistui!:\n" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * Lataa uuden tuotelistan tietokannasta ja päivittää tuotteet-taulukon
     */
    public void paivitaTuotteetTaulukko() {
        DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
        // Poista entiset rivit
        for (int i = jtblTuotteet.getRowCount() -1; i >= 0; i--) {
            model.removeRow(i);
        }
        tuoteLista.lataaUusiTuotelista();

        for (Tuote tu : tuoteLista.getTUOTTEET()) {
            Object[] row = new Object[3];
            row[0] = tu.getTUOTENUMERO();
            row[1] = tu.getTUOTENIMI();
            row[2] = tu.getAHINTA();
            model.addRow(row);
        }
    }
    /**

    /**
     * Creates new form Tuoterekisteri
     */
    TuoteLista tuoteLista;
    public Tuoterekisteri() {
        initComponents();
        tuoteLista = new TuoteLista();
        paivitaTuotteetTaulukko();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTuotteet = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtxtNimi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtHinta = new javax.swing.JTextField();
        jtbnLisaa = new javax.swing.JButton();
        jtbnPaivita = new javax.swing.JButton();
        jtbnPoista = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtxtTuotenro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tuoterekisteri");

        jtblTuotteet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tuotenumero", "Nimi", "A'-Hinta"
            }
        ));
        jtblTuotteet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTuotteetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTuotteet);

        jLabel1.setText("Tuotteen nimi:");

        jLabel2.setText("Tuotteen hinta:");

        jtbnLisaa.setText("Lisää");
        jtbnLisaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnLisaaActionPerformed(evt);
            }
        });

        jtbnPaivita.setText("Päivitä");
        jtbnPaivita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnPaivitaActionPerformed(evt);
            }
        });

        jtbnPoista.setText("Poista");
        jtbnPoista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnPoistaActionPerformed(evt);
            }
        });

        jLabel3.setText("Tuotenumero:");

        jtxtTuotenro.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtbnLisaa)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnPaivita)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnPoista))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtxtTuotenro)
                            .addComponent(jtxtNimi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtHinta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtxtTuotenro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtNimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtHinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtbnLisaa)
                            .addComponent(jtbnPaivita)
                            .addComponent(jtbnPoista))))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Tarkistaa onko tekstikentät täytetty asianmukaisesti
     * @return Palauttaa true jos tekstikentät täytetty oikein
     */
    private boolean tarkistaTekstikentat() {
        if (jtxtNimi.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(jtxtHinta.getText());
            Integer.parseInt(jtxtTuotenro.getText());
        } catch (Exception e) {
           return false;
        }
        return true;
    }

    private void jtblTuotteetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTuotteetMouseClicked
        int i = jtblTuotteet.getSelectedRow();
        Tuote tuote = tuoteLista.getTUOTTEET().get(i);

        jtxtTuotenro.setText(String.valueOf(tuote.getTUOTENUMERO()));
        jtxtNimi.setText(tuote.getTUOTENIMI());
        jtxtHinta.setText(String.valueOf(tuote.getAHINTA()));
    }//GEN-LAST:event_jtblTuotteetMouseClicked

    private void jtbnLisaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnLisaaActionPerformed
        if(!tarkistaTekstikentat()) {
            JOptionPane.showInternalMessageDialog(null, "Tekstikentät ei ole täytetty oikein!\nTuotetta ei lisätty.", "Virhe", JOptionPane.ERROR_MESSAGE);
        }
        tuoteLista.lisaaTuote(jtxtNimi.getText(), Integer.valueOf(jtxtHinta.getText()));
    }//GEN-LAST:event_jtbnLisaaActionPerformed

    private void jtbnPaivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnPaivitaActionPerformed
        if(!tarkistaTekstikentat()) {
            JOptionPane.showInternalMessageDialog(null, "Tekstikentät ei ole täytetty oikein!\nTuotetta ei päivitetty.", "Virhe", JOptionPane.ERROR_MESSAGE);
        }
        tuoteLista.paivitaTuote(Integer.valueOf(jtxtTuotenro.getText()), jtxtNimi.getText(), Integer.valueOf(jtxtHinta.getText()));
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbnPaivitaActionPerformed

    private void jtbnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnPoistaActionPerformed
        if (jtblTuotteet.getRowCount() == 0) {
            JOptionPane.showInternalMessageDialog(null, "Ei poistettavia tuotteita.", "Virhe", JOptionPane.ERROR_MESSAGE);
        }
        tuoteLista.poistaTuote(Integer.valueOf(jtxtTuotenro.getText()));
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbnPoistaActionPerformed

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
            java.util.logging.Logger.getLogger(Tuoterekisteri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tuoterekisteri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tuoterekisteri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tuoterekisteri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tuoterekisteri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblTuotteet;
    private javax.swing.JButton jtbnLisaa;
    private javax.swing.JButton jtbnPaivita;
    private javax.swing.JButton jtbnPoista;
    private javax.swing.JTextField jtxtHinta;
    private javax.swing.JTextField jtxtNimi;
    private javax.swing.JTextField jtxtTuotenro;
    // End of variables declaration//GEN-END:variables
}
