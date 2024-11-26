/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.asiakasrekisteri;

import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.jani.asiakasrekisteri.Asiakasrekisterihallinta.Asiakas;
/**
 *
 * @author Jani
 */
public class Tuoterekisterihallinta extends javax.swing.JFrame {
    class Tilaus {
        private int ASIAKASNUMERO;
        private int TUOTENUMERO;
        private int MAKSUTAPANUMERO;
        private int MAARA;
        private int AHINTA;
        private String TILAUSPAIVA;
        private String TOIMITUSPAIVA;
        private String TOIMITUSTAPA;
        private String ERAPAIVA;
        private String LISATIETOJA;

        public int getASIAKASNUMERO() {
            return ASIAKASNUMERO;
        }
        public String getTOIMITUSTAPA() {
            return TOIMITUSTAPA;
        }
        public String getLISATIETOJA() {
            return LISATIETOJA;
        }
        public String getERAPAIVA() {
            return ERAPAIVA;
        }
        public String getTOIMITUSPAIVA() {
            return TOIMITUSPAIVA;
        }
        public String getTILAUSPAIVA() {
            return TILAUSPAIVA;
        }
        public int getTUOTENUMERO() {
            return TUOTENUMERO;
        }

        public int getMAKSUTAPANUMERO() {
            return MAKSUTAPANUMERO;
        }

        public int getMAARA() {
            return MAARA;
        }

        public int getAHINTA() {
            return AHINTA;
        }

        public Tilaus (int asiakasnumero, int tuotenumero, int maksutapanumero, int maara, int ahinta, String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
            this.ASIAKASNUMERO = asiakasnumero;
            this.TUOTENUMERO = tuotenumero;
            this.MAKSUTAPANUMERO = maksutapanumero;
            this.MAARA = maara;
            this.AHINTA = ahinta;
            this.TILAUSPAIVA = tilauspaiva;
            this.TOIMITUSPAIVA = toimituspaiva;
            this.TOIMITUSTAPA = toimitustapa;
            this.ERAPAIVA = erapaiva;
            this.LISATIETOJA = lisatietoja;
        }

        public void paivitaTilaus(int asiakasnumero, int tuotenumero, int maksutapanumero, int maara, int ahinta, String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
            this.ASIAKASNUMERO = asiakasnumero;
            this.TUOTENUMERO = tuotenumero;
            this.MAKSUTAPANUMERO = maksutapanumero;
            this.MAARA = maara;
            this.AHINTA = ahinta;
            this.TILAUSPAIVA = tilauspaiva;
            this.TOIMITUSPAIVA = toimituspaiva;
            this.TOIMITUSTAPA = toimitustapa;
            this.ERAPAIVA = erapaiva;
            this.LISATIETOJA = lisatietoja;
        }
    }
    class TilausLista {
        private ArrayList<Tilaus> TILAUKSET;

        public TilausLista() {
            this.TILAUKSET = new ArrayList<>();
        }

        public void lisaaTilaus(Tilaus tilaus) {
            this.TILAUKSET.add(tilaus);
        }
        public Tilaus haeTilaus(int indeksi) {
            return this.TILAUKSET.get(indeksi);
        }
        public int haeTilauslistaPituus() {
            return this.TILAUKSET.size();
        }
        public void poistaTilaus(int indeksi) {
            this.TILAUKSET.remove(indeksi);
        }
                /**
         * Tyhjennä nykyinen tilauslista ja hae uusi lista tietokannasta
         */
        public void HaeTilauksetTietokannasta() {
            Connection yhteys = luoYhteys();
            this.TILAUKSET.clear();

            String query = "SELECT ASIAKASNUMERO, TUOTENUMERO, MAKSUTAPANUMERO, MAARA, AHINTA, TILAUSPAIVA, ERAPAIVA, TOIMITUSPAIVA, TOIMITUSTAPA, LISATIETOJA FROM `tilaus` ";
            query += "INNER JOIN tilausrivi ";
            query += "ON (tilaus.TILAUSNUMERO = tilausrivi.TILAUSNUMERO)";
            Statement st;
            ResultSet rs;
            try {
                st = yhteys.createStatement();
                rs = st.executeQuery(query);

                Tilaus tl;
                while (rs.next()) {
                    tl = new Tilaus(
                    rs.getInt("ASIAKASNUMERO"),
                    rs.getInt("TUOTENUMERO"),
                    rs.getInt("MAKSUTAPANUMERO"),
                    rs.getInt("MAARA"),
                    rs.getInt("AHINTA"),
                    rs.getDate("TILAUSPAIVA").toString(),
                    rs.getDate("ERAPAIVA").toString(),
                    rs.getDate("TOIMITUSPAIVA").toString(),
                    rs.getString("TOIMITUSTAPA"),
                    rs.getString("LISATIETOJA")
                    );
                    this.TILAUKSET.add(tl);
                }
            } catch (SQLException e) {
                System.out.println("Virhe asiakastaulukon haussa!");
                e.printStackTrace();
            }
        }
    }
    /**
     * Creates new form Tuoterekisterihallinta
     */
    TilausLista tilausLista;
    public Tuoterekisterihallinta() {
        initComponents();
        tilausLista = new TilausLista();
        paivitaTuotteetTaulukkoUI();
    }
    private boolean tarkistaTekstikentat() {
        // TODO
        return true;
    }

    private void paivitaTuotteetTaulukkoUI() {
        
        DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
        // Poista entiset rivit
        for (int i = jtblTuotteet.getRowCount() -1; i >= 0; i--) {
            model.removeRow(i);
        }
        tilausLista.HaeTilauksetTietokannasta();

        for (int i = 0; i < tilausLista.haeTilauslistaPituus(); i++) {
            Object[] row = new Object[10];
            // Lisää uudet asiakkaat
            //asiakasLista.HaeAsiakkaatTietokannasta();
            Tilaus tl = tilausLista.haeTilaus(i);
            row[0] = tl.getASIAKASNUMERO();
            row[1] = tl.getTUOTENUMERO();
            row[2] = tl.getMAARA();
            row[3] = tl.getAHINTA();
            row[4] = tl.getMAKSUTAPANUMERO();
            row[5] = tl.getTILAUSPAIVA();
            row[6] = tl.getERAPAIVA();
            row[7] = tl.getTOIMITUSPAIVA();
            row[8] = tl.getTOIMITUSTAPA();
            row[9] = tl.getLISATIETOJA();
            model.addRow(row);
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
            cn = DriverManager.getConnection("jdbc:mariadb://" + "localhost" + ":3306/ASIAKASTILAUSJARJESTELMA" + "?socketTimeout=2000", kayttaja, "SalaSana123!");
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
     * Suorittaa SQL kyselyn turvallisesti PreparedStatement avulla, näyttää käyttäjälle annetun viestin lopuksi
     * @param kysely Suoritettava kysely
     * @param viesti Käyttäjälle näytettävä viesti
     */
    public void suoritaTurvallinenSQLKysely(PreparedStatement kysely, Connection yhteys, String viesti) {
        try {
            int tulos = kysely.executeUpdate();

            if (tulos > 0) {
                // Päivitä taulukko
                DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
                model.setRowCount(0);
                paivitaTuotteetTaulukkoUI();

                JOptionPane.showMessageDialog(null, "Data " + viesti + " onnistuneesti");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data ei " + viesti);
            }
            yhteys.close();
        } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Data ei " + viesti);
        }
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
        jtxtAsiakasnro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtTuotenro = new javax.swing.JTextField();
        jtxtMaara = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtxtHinta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtTilauspaiva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtxtErapaiva = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtToimituspaiva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtxtToimitustapa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtLisatietoja = new javax.swing.JTextField();
        jtbnUusi = new javax.swing.JButton();
        jtbnPaivita = new javax.swing.JButton();
        jtbnPoista = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jcbMaksutapa = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtblTuotteet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Asiakasnumero", "Tuotenumero", "Määrä", "A'-Hinta", "Maksutapa", "Tilauspäivä", "Eräpäivä", "Toimituspäivä", "Toimitustapa", "Lisätietoja"
            }
        ));
        jtblTuotteet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTuotteetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTuotteet);

        jLabel1.setText("Asiakasnumero:");

        jLabel2.setText("Tuotenumero:");

        jLabel3.setText("Määrä:");

        jLabel4.setText("A-hinta:");

        jLabel5.setText("Tilauspäivä:");

        jLabel6.setText("Eräpäivä:");

        jLabel7.setText("Toimituspäivä:");

        jLabel8.setText("Toimitustapa:");

        jLabel9.setText("Lisätietoja:");

        jtbnUusi.setText("Uusi");

        jtbnPaivita.setText("Päivitä");

        jtbnPoista.setText("Poista");

        jLabel10.setText("Maksutapa:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtxtAsiakasnro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtTuotenro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtMaara, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtHinta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtErapaiva, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtToimituspaiva, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtToimitustapa, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtLisatietoja, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtxtTilauspaiva, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcbMaksutapa, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jtbnUusi)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnPaivita)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnPoista)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtAsiakasnro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtTuotenro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtxtMaara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtxtHinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jcbMaksutapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtxtTilauspaiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtxtErapaiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtxtToimituspaiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtxtToimitustapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jtxtLisatietoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtbnUusi)
                            .addComponent(jtbnPaivita)
                            .addComponent(jtbnPoista))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtblTuotteetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTuotteetMouseClicked
        int i = jtblTuotteet.getSelectedRow();
        Tilaus tl = tilausLista.haeTilaus(i);

        jtxtAsiakasnro.setText(String.valueOf(tl.getASIAKASNUMERO()));
        jtxtTuotenro.setText(String.valueOf(tl.getTUOTENUMERO()));
        jtxtMaara.setText(String.valueOf(tl.getMAARA()));
        jtxtHinta.setText(String.valueOf(tl.getAHINTA()));
        // TODO maksutapa
        jtxtTilauspaiva.setText(tl.getTILAUSPAIVA());
        jtxtErapaiva.setText(tl.getERAPAIVA());
        jtxtToimituspaiva.setText(tl.getTOIMITUSPAIVA());
        jtxtToimitustapa.setText(tl.getTOIMITUSTAPA());
        jtxtLisatietoja.setText(tl.getLISATIETOJA());
    }//GEN-LAST:event_jtblTuotteetMouseClicked

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
            java.util.logging.Logger.getLogger(Tuoterekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tuoterekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tuoterekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tuoterekisterihallinta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tuoterekisterihallinta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbMaksutapa;
    private javax.swing.JTable jtblTuotteet;
    private javax.swing.JButton jtbnPaivita;
    private javax.swing.JButton jtbnPoista;
    private javax.swing.JButton jtbnUusi;
    private javax.swing.JTextField jtxtAsiakasnro;
    private javax.swing.JTextField jtxtErapaiva;
    private javax.swing.JTextField jtxtHinta;
    private javax.swing.JTextField jtxtLisatietoja;
    private javax.swing.JTextField jtxtMaara;
    private javax.swing.JTextField jtxtTilauspaiva;
    private javax.swing.JTextField jtxtToimituspaiva;
    private javax.swing.JTextField jtxtToimitustapa;
    private javax.swing.JTextField jtxtTuotenro;
    // End of variables declaration//GEN-END:variables
}
