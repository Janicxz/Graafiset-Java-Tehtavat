/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.asiakasrekisteri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jani
 */
public class Asiakasrekisterihallinta extends javax.swing.JFrame {

    class Asiakas {
        private int ASIAKASNUMERO;
        private String ETUNIMI;
        private String SUKUNIMI;
        private String YRITYS;
        private String KATUOSOITE;
        private String POSTINUMERO;
        private String POSTITOIMIPAIKKA;
        private String PUHELIN;
        private String EMAIL;

        public Asiakas(int id, String etunimi, String sukunimi, String yritys, String Katuosoite, String Postinumero, String Postitoimipaikka, String Puhelin, String Email) {
            this.ASIAKASNUMERO = id;
            this.ETUNIMI = etunimi;
            this.SUKUNIMI = sukunimi;
            this.YRITYS = yritys;
            this.KATUOSOITE = Katuosoite;
            this.POSTINUMERO = Postinumero;
            this.POSTITOIMIPAIKKA = Postitoimipaikka;
            this.PUHELIN = Puhelin;
            this.EMAIL = Email;
        }

        public int HaeAsiakasnumero() {
            return this.ASIAKASNUMERO;
        }
        public String HaeEtunimi() {
            return this.ETUNIMI;
        }
        public String HaeSukunimi() {
            return this.SUKUNIMI;
        }
        public String HaeYritys() {
            return this.YRITYS;
        }
        public String HaeKatuosoite() {
            return this.KATUOSOITE;
        }
        public String HaePostinumero() {
            return this.POSTINUMERO;
        }
        public String HaePostitoimipaikka() {
            return this.POSTITOIMIPAIKKA;
        }
        public String HaePuhelin() {
            return this.PUHELIN;
        }
        public String HaeEmail() {
            return this.EMAIL;
        }
    }
    /**
     * Creates new form Asiakasrekisterihallinta
     */
    public Asiakasrekisterihallinta() {
        initComponents();
        NaytaAsiakkaat();
    }
    
    public Connection luoYhteys() {
        Connection cn = null;
        try {
            String kayttaja = "kehittaja";
            cn = DriverManager.getConnection("jdbc:mariadb://" + "localhost" + ":3306/ASIAKASTILAUSJARJESTELMA" + "?socketTimeout=2000", kayttaja, "SalaSana123!");
            return cn;
        }
        catch(SQLException ex) {
            System.out.println("Yhteyden luominen epäonnistui!:\n" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Asiakas> HaeAsiakasTaulukko() {
        ArrayList<Asiakas> Asiakastaulukko = new ArrayList<Asiakas>();

        Connection yhteys = luoYhteys();

        String query = "SELECT ASIAKASNUMERO, ETUNIMI, SUKUNIMI, YRITYS, KATUOSOITE, POSTINUMERO, POSTITOIMIPAIKKA, PUHELIN, EMAIL FROM ASIAKAS";
        Statement st;
        ResultSet rs;
        try {
            st = yhteys.createStatement();
            rs = st.executeQuery(query);

            Asiakas as;
            while (rs.next()) {
                as = new Asiakas(
                rs.getInt("ASIAKASNUMERO"),
                rs.getString("ETUNIMI"),
                rs.getString("SUKUNIMI"),
                rs.getString("YRITYS"),
                rs.getString("KATUOSOITE"),
                rs.getString("POSTINUMERO"),
                rs.getString("POSTITOIMIPAIKKA"),
                rs.getString("PUHELIN"),
                rs.getString("EMAIL")
                );
                Asiakastaulukko.add(as);
            }
        } catch (SQLException e) {
            System.out.println("Virhe asiakastaulukon haussa!");
            e.printStackTrace();
        }
        return Asiakastaulukko;
    }

    public void NaytaAsiakkaat() {
        ArrayList<Asiakas> list = HaeAsiakasTaulukko();
        DefaultTableModel model = (DefaultTableModel)jtblAsiakkaat.getModel();
        
        Object[] row = new Object[9];
        // Poista entiset rivit
        for (int i = jtblAsiakkaat.getRowCount() -1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).HaeAsiakasnumero();
            row[1] = list.get(i).HaeEtunimi();
            row[2] = list.get(i).HaeSukunimi();
            row[3] = list.get(i).HaeYritys();
            row[4] = list.get(i).HaeKatuosoite();
            row[5] = list.get(i).HaePostinumero();
            row[6] = list.get(i).HaePostitoimipaikka();
            row[7] = list.get(i).HaePuhelin();
            row[8] = list.get(i).HaeEmail();
            model.addRow(row);
        }
    }

    public void suoritaSQLKysely(String query, String message) {
        Connection yhteys = luoYhteys();
        Statement st;
        try {
            st = yhteys.createStatement();
            if(st.executeUpdate(query) == 1) {
                // Päivitä taulukko
                DefaultTableModel model = (DefaultTableModel)jtblAsiakkaat.getModel();
                model.setRowCount(0);
                NaytaAsiakkaat();

                JOptionPane.showMessageDialog(null, "Data " + message + " onnistuneesti");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data ei " + message);
            }
            yhteys.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean onkoAsiakastiedotTaytetty() {
        if (jtxtEtunimi.getText().equals("") || jtxtSukunimi.getText().equals("") || jtxtYritys.getText().equals("") 
        || jtxtKatuosoite.getText().equals("") || jtxtKatuosoite.getText().equals("")
        || jtxtPostinumero.getText().equals("") || jtxtPostitoimipaikka.getText().equals("") ||
        jtxtPuhelin.getText().equals("") || jtxtEmail.getText().equals("")) {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtAsiakasnumero = new javax.swing.JTextField();
        jtxtEtunimi = new javax.swing.JTextField();
        jtxtSukunimi = new javax.swing.JTextField();
        jtxtYritys = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAsiakkaat = new javax.swing.JTable();
        jbtnUusi = new javax.swing.JButton();
        jbtnPaivita = new javax.swing.JButton();
        jbtnPoista = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jtxtKatuosoite = new javax.swing.JTextField();
        jtxtPostinumero = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtPostitoimipaikka = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtPuhelin = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtxtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asiakasrekisteri");

        jLabel1.setText("Asiakasnumero:");

        jLabel2.setText("Etunimi:");

        jLabel3.setText("Sukunimi:");

        jLabel4.setText("Yritys:");

        jtblAsiakkaat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Asiakasnumero", "Etunimi", "Sukunimi", "Yritys", "Katuosoite", "Postinumero", "Postitoimipaikka", "Puhelin", "Email"
            }
        ));
        jtblAsiakkaat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAsiakkaatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblAsiakkaat);

        jbtnUusi.setText("Uusi");
        jbtnUusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUusiActionPerformed(evt);
            }
        });

        jbtnPaivita.setText("Päivitä");
        jbtnPaivita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPaivitaActionPerformed(evt);
            }
        });

        jbtnPoista.setText("Poista");
        jbtnPoista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPoistaActionPerformed(evt);
            }
        });

        jLabel5.setText("Katuosoite:");

        jtxtPostinumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPostinumeroActionPerformed(evt);
            }
        });

        jLabel6.setText("Postinumero:");

        jLabel7.setText("Postitoimipaikka:");

        jLabel8.setText("Puhelin:");

        jLabel9.setText("Email:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbtnUusi)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnPaivita)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnPoista))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxtKatuosoite, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtSukunimi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtEtunimi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtYritys, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxtPostinumero, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxtPostitoimipaikka, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxtPuhelin, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jtxtEtunimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jtxtSukunimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jtxtYritys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtKatuosoite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtPostinumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtPostitoimipaikka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtPuhelin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnUusi)
                    .addComponent(jbtnPaivita)
                    .addComponent(jbtnPoista))
                .addGap(88, 88, 88))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtblAsiakkaatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAsiakkaatMouseClicked
        // Hae valittu rivi
        int i = jtblAsiakkaat.getSelectedRow();
        TableModel model = jtblAsiakkaat.getModel();
        // Päivitä käyttöliittymän tiedot
        jtxtAsiakasnumero.setText(model.getValueAt(i, 0).toString());
        jtxtEtunimi.setText(model.getValueAt(i, 1).toString());
        jtxtSukunimi.setText(model.getValueAt(i, 2).toString());
        jtxtYritys.setText(model.getValueAt(i, 3).toString());
        jtxtKatuosoite.setText(model.getValueAt(i, 4).toString());
        jtxtPostinumero.setText(model.getValueAt(i, 5).toString());
        jtxtPostitoimipaikka.setText(model.getValueAt(i, 6).toString());
        jtxtPuhelin.setText(model.getValueAt(i, 7).toString());
        jtxtEmail.setText(model.getValueAt(i, 8).toString());
    }//GEN-LAST:event_jtblAsiakkaatMouseClicked

    private void jbtnUusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUusiActionPerformed
        // Lisää uusi asiakas tietokantaan.
        if (!onkoAsiakastiedotTaytetty()) {
            JOptionPane.showInternalMessageDialog(null, "Virhe lisättäessä uutta asiakasta!\n Jokin tekstikenttä on tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date tamaPaiva = new Date();
        // TODO: PreparedStatement
        if (JOptionPane.showConfirmDialog(null, "Haluatko lisätä uuden asiakkaan " + "(" + jtxtAsiakasnumero.getText() + ") "  + jtxtEtunimi.getText() + " " + jtxtSukunimi.getText() + "?", "Lisää", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            String query = "INSERT INTO `ASIAKAS`(`ASIAKKAAKSITULOPAIVA`, `YRITYS`, `ETUNIMI`, `SUKUNIMI`, `KATUOSOITE`, `POSTINUMERO`, `POSTITOIMIPAIKKA`, `PUHELIN`, `EMAIL`)";
            query += " VALUES('" + dateFormat.format(tamaPaiva) + "','" + jtxtYritys.getText() + "','" + jtxtEtunimi.getText() + "','" + 
                        jtxtSukunimi.getText() + "', '', '', '', '', '')";
            System.out.println(query);
            suoritaSQLKysely(query, "lisätty");
        }
    }//GEN-LAST:event_jbtnUusiActionPerformed

    private void jbtnPaivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPaivitaActionPerformed
        // Päivitä valitun asiakkaan tiedot.
        if (!onkoAsiakastiedotTaytetty()) {
            JOptionPane.showInternalMessageDialog(null, "Virhe päivitettäessä asiakkaan tietoja!\n Jokin tekstikenttä on tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (JOptionPane.showConfirmDialog(null, "Haluatko päivittää asiakkaan " + "(" + jtxtAsiakasnumero.getText() + ") "  + jtxtEtunimi.getText() + " " + jtxtSukunimi.getText() + " tiedot?", "Päivitä", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            // TODO: PreparedStatement
            String query = "UPDATE `ASIAKAS` SET ETUNIMI='" + jtxtEtunimi.getText() +
            "', SUKUNIMI='" + jtxtSukunimi.getText() +
            "', YRITYS='" + jtxtYritys.getText() +
            "' WHERE ASIAKASNUMERO=" + jtxtAsiakasnumero.getText();
            suoritaSQLKysely(query, "päivitetty");
        }
    }//GEN-LAST:event_jbtnPaivitaActionPerformed

    private void jbtnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPoistaActionPerformed

        // TODO: PreparedStatement
        // Poista valittu asiakas tietokannasta.
        if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistaa asiakkaan " + "(" + jtxtAsiakasnumero.getText() + ") " + jtxtEtunimi.getText() + " " + jtxtSukunimi.getText() + "?", "Poista", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            String query = "DELETE FROM ASIAKAS WHERE ASIAKASNUMERO=" + jtxtAsiakasnumero.getText();
            suoritaSQLKysely(query, "poistettu");
        }

    }//GEN-LAST:event_jbtnPoistaActionPerformed

    private void jtxtPostinumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPostinumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPostinumeroActionPerformed

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
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asiakasrekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asiakasrekisterihallinta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnPaivita;
    private javax.swing.JButton jbtnPoista;
    private javax.swing.JButton jbtnUusi;
    private javax.swing.JTable jtblAsiakkaat;
    private javax.swing.JTextField jtxtAsiakasnumero;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtEtunimi;
    private javax.swing.JTextField jtxtKatuosoite;
    private javax.swing.JTextField jtxtPostinumero;
    private javax.swing.JTextField jtxtPostitoimipaikka;
    private javax.swing.JTextField jtxtPuhelin;
    private javax.swing.JTextField jtxtSukunimi;
    private javax.swing.JTextField jtxtYritys;
    // End of variables declaration//GEN-END:variables
}
