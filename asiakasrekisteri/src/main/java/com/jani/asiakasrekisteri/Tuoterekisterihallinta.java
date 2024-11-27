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
        private int TILAUSNUMERO;
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
        public int getTILAUSNUMERO() {
            return TILAUSNUMERO;
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

        public Tilaus (int asiakasnumero, int tilausnumero, int tuotenumero, int maksutapanumero, int maara, int ahinta, String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
            this.ASIAKASNUMERO = asiakasnumero;
            this.TILAUSNUMERO = tilausnumero;
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

        public void lisaaTilaus(int tilausnumero,int asiakasnumero, int tuotenumero, int maksutapanumero, int maara, int ahinta,
        String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
            // TODO
            String query = "INSERT INTO tilaus(`ASIAKASNUMERO`, `MAKSUTAPANUMERO`, `TILAUSPAIVA`, `TOIMITUSPAIVA`, `TOIMITUSTAPA`, `ERAPAIVA`, `LISATIETOJA`) ";
            query += "VALUES(?,?,?,?,?,?,?);";
            try {
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(query);
                st.setInt(1, asiakasnumero);
                st.setInt(2, maksutapanumero);
                st.setDate(3, java.sql.Date.valueOf(tilauspaiva));
                st.setDate(4, java.sql.Date.valueOf(toimituspaiva));
                st.setString(5, toimitustapa);
                st.setDate(6, java.sql.Date.valueOf(erapaiva));
                st.setString(7, lisatietoja);

                System.out.println(st.toString());
                suoritaTurvallinenSQLKysely(st, yhteys, "Lisätty", false);
                
                query = "INSERT INTO tilausrivi(`TILAUSNUMERO`, `TUOTENUMERO`, `MAARA`, `AHINTA`) VALUES(LAST_INSERT_ID(), ?, ?, ?);";
                st = yhteys.prepareStatement(query);
                st.setInt(1, tuotenumero);
                st.setInt(2, maara);
                st.setInt(3, ahinta);
                suoritaTurvallinenSQLKysely(st, yhteys, "Lisätty", true);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //this.TILAUKSET.add(tilaus);
        }
        public void paivitaTilaus(int tilausnumero, int asiakasnumero, int tuotenumero, int maksutapanumero, int maara, int ahinta, String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
            String query = "UPDATE `tilaus` SET ASIAKASNUMERO=?, MAKSUTAPANUMERO=?, TILAUSPAIVA=?, TOIMITUSPAIVA=?, TOIMITUSTAPA=?, ERAPAIVA=?, LISATIETOJA=?";
            query += "WHERE tilausnumero = ?";
            
            try {
                Connection yhteys = luoYhteys();
                PreparedStatement st;
                st = yhteys.prepareStatement(query);
                st.setInt(1, asiakasnumero);
                st.setInt(2, maksutapanumero);
                st.setDate(3, java.sql.Date.valueOf(tilauspaiva));
                st.setDate(4, java.sql.Date.valueOf(toimituspaiva));
                st.setString(5, toimitustapa);
                st.setDate(6, java.sql.Date.valueOf(erapaiva));
                st.setString(7, lisatietoja);
                st.setInt(8, tilausnumero);
                suoritaTurvallinenSQLKysely(st, yhteys, query, false);

                query = "UPDATE `tilausrivi` SET TUOTENUMERO=?, MAARA=?, AHINTA=? WHERE TILAUSNUMERO=?";
                st = yhteys.prepareStatement(query);
                st.setInt(1, tuotenumero);
                st.setInt(2, maara);
                st.setInt(3, ahinta);
                st.setInt(4, tilausnumero);
                System.out.println(st.toString());
                suoritaTurvallinenSQLKysely(st, yhteys, "päivitetty", true);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public Tilaus haeTilaus(int indeksi) {
            return this.TILAUKSET.get(indeksi);
        }
        public int haeTilauslistaPituus() {
            return this.TILAUKSET.size();
        }
        public void poistaTilaus(int indeksi) {
            System.out.println("indeksi: " + indeksi);
            int tlNumero = this.TILAUKSET.get(indeksi).getTILAUSNUMERO();
            String query = "DELETE FROM tilausrivi WHERE `TILAUSNUMERO` = ?";

            try {
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(query);
                st.setInt(1, tlNumero);
                suoritaTurvallinenSQLKysely(st, yhteys, "Poistettu", true);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /**
         * Tyhjennä nykyinen tilauslista ja hae uusi lista tietokannasta
         */
        public void HaeTilauksetTietokannasta() {
            Connection yhteys = luoYhteys();
            this.TILAUKSET.clear();

            String query = "SELECT ASIAKASNUMERO, tilaus.TILAUSNUMERO, TUOTENUMERO, MAKSUTAPANUMERO, MAARA, AHINTA, TILAUSPAIVA, ERAPAIVA, TOIMITUSPAIVA, TOIMITUSTAPA, LISATIETOJA FROM `tilaus` ";
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
                    rs.getInt("tilaus.TILAUSNUMERO"),
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
    private boolean tarkistaTekstikentatTaytetty() {
        // TODO
        if (jtxtAsiakasnro.getText().equals("") || jtxtTuotenro.getText().equals("") || jtxtMaara.getText().equals("") || 
            jtxtHinta.getText().equals("") || jtxtTilauspaiva.getText().equals("") || jtxtErapaiva.getText().equals("") ||
            jtxtToimituspaiva.getText().equals("") || jtxtToimitustapa.getText().equals("")) {
                return false;
            }
        else {
            return true;
        }
    }

    private void paivitaTuotteetTaulukkoUI() {
        
        DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
        // Poista entiset rivit
        for (int i = jtblTuotteet.getRowCount() -1; i >= 0; i--) {
            model.removeRow(i);
        }
        tilausLista.HaeTilauksetTietokannasta();

        for (int i = 0; i < tilausLista.haeTilauslistaPituus(); i++) {
            Object[] row = new Object[11];
            // Lisää uudet asiakkaat
            //asiakasLista.HaeAsiakkaatTietokannasta();
            Tilaus tl = tilausLista.haeTilaus(i);
            row[0] = tl.getASIAKASNUMERO();
            row[1] = tl.getTILAUSNUMERO();
            row[2] = tl.getTUOTENUMERO();
            row[3] = tl.getMAARA();
            row[4] = tl.getAHINTA();
            row[5] = tl.getMAKSUTAPANUMERO();
            row[6] = tl.getTILAUSPAIVA();
            row[7] = tl.getERAPAIVA();
            row[8] = tl.getTOIMITUSPAIVA();
            row[9] = tl.getTOIMITUSTAPA();
            row[10] = tl.getLISATIETOJA();
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
    public void suoritaTurvallinenSQLKysely(PreparedStatement kysely, Connection yhteys, String viesti, boolean suljeYhteys) {
        try {
            int tulos = kysely.executeUpdate();

            if (tulos > 0) {
                // Päivitä taulukko
                DefaultTableModel model = (DefaultTableModel)jtblTuotteet.getModel();
                model.setRowCount(0);
                paivitaTuotteetTaulukkoUI();
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
        jLabel11 = new javax.swing.JLabel();
        jtxtTilausnro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtblTuotteet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Asiakasnumero", "Tilausnumero", "Tuotenumero", "Määrä", "A'-Hinta", "Maksutapa", "Tilauspäivä", "Eräpäivä", "Toimituspäivä", "Toimitustapa", "Lisätietoja"
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
        jtbnUusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnUusiActionPerformed(evt);
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

        jLabel10.setText("Maksutapa:");

        jLabel11.setText("Tilausnumero:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
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
                            .addComponent(jcbMaksutapa, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jtbnUusi)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnPaivita)
                        .addGap(18, 18, 18)
                        .addComponent(jtbnPoista))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtTilausnro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtxtAsiakasnro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jtxtTilausnro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
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
        jtxtTilausnro.setText(String.valueOf(tl.getTILAUSNUMERO()));
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

    private void jtbnPaivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnPaivitaActionPerformed
       if (!tarkistaTekstikentatTaytetty()) {
            JOptionPane.showInternalMessageDialog(null, "Kaikki tekstikentät ei ole täytetty!", "", JOptionPane.ERROR_MESSAGE);
       }
       int i = jtblTuotteet.getSelectedRow();
       try {
        // TODO maksutapa
            tilausLista.paivitaTilaus(Integer.parseInt(jtxtTilausnro.getText()), Integer.parseInt(jtxtAsiakasnro.getText()),
            Integer.parseInt(jtxtTuotenro.getText()), 1, Integer.parseInt(jtxtMaara.getText()), Integer.parseInt(jtxtHinta.getText()),
            jtxtTilauspaiva.getText(), jtxtErapaiva.getText(), jtxtToimituspaiva.getText(), jtxtToimitustapa.getText(), jtxtLisatietoja.getText());
        
       } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, "Virhe tilauksen päivityksessä!", "", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jtbnPaivitaActionPerformed

    private void jtbnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnPoistaActionPerformed
        int i = jtblTuotteet.getSelectedRow();
        tilausLista.poistaTilaus(i);
        //JOptionPane.showInternalMessageDialog(null, "Tilaus poistettu.");
    }//GEN-LAST:event_jtbnPoistaActionPerformed

    private void jtbnUusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnUusiActionPerformed
        int tilausnro = 0;
        try {
            tilausnro = Integer.parseInt(jtxtTilausnro.getText());
        } catch (Exception e) {
            // TODO: handle exception
        }
        tilausLista.lisaaTilaus(tilausnro,Integer.parseInt(jtxtAsiakasnro.getText()),
        Integer.parseInt(jtxtTuotenro.getText()), 1, Integer.parseInt(jtxtMaara.getText()), Integer.parseInt(jtxtHinta.getText()),
        jtxtTilauspaiva.getText(), jtxtErapaiva.getText(), jtxtToimituspaiva.getText(), jtxtToimitustapa.getText(), jtxtLisatietoja.getText());
    }//GEN-LAST:event_jtbnUusiActionPerformed

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
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField jtxtTilausnro;
    private javax.swing.JTextField jtxtTilauspaiva;
    private javax.swing.JTextField jtxtToimituspaiva;
    private javax.swing.JTextField jtxtToimitustapa;
    private javax.swing.JTextField jtxtTuotenro;
    // End of variables declaration//GEN-END:variables
}
