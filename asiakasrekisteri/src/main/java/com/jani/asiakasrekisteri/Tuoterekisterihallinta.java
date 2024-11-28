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
import javax.swing.table.AbstractTableModel;

import com.jani.asiakasrekisteri.Asiakasrekisterihallinta.Asiakas;
/**
 *
 * @author Jani
 */
public class Tuoterekisterihallinta extends javax.swing.JFrame {
    /**
     * Sisältää tilauksiin liittyvän datan
     */
    class Tilaus {
        private int ASIAKASNUMERO;
        private int TILAUSNUMERO;
        private int MAKSUTAPANUMERO;
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
        public int getMAKSUTAPANUMERO() {
            return MAKSUTAPANUMERO;
        }

        public Tilaus (int asiakasnumero, int tilausnumero, int maksutapanumero, String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
            this.ASIAKASNUMERO = asiakasnumero;
            this.TILAUSNUMERO = tilausnumero;
            this.MAKSUTAPANUMERO = maksutapanumero;
            this.TILAUSPAIVA = tilauspaiva;
            this.TOIMITUSPAIVA = toimituspaiva;
            this.TOIMITUSTAPA = toimitustapa;
            this.ERAPAIVA = erapaiva;
            this.LISATIETOJA = lisatietoja;
        }

        public void paivitaTilaus(int asiakasnumero, int maksutapanumero, String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
            this.ASIAKASNUMERO = asiakasnumero;
            this.MAKSUTAPANUMERO = maksutapanumero;
            this.TILAUSPAIVA = tilauspaiva;
            this.TOIMITUSPAIVA = toimituspaiva;
            this.TOIMITUSTAPA = toimitustapa;
            this.ERAPAIVA = erapaiva;
            this.LISATIETOJA = lisatietoja;
        }
    }
    /**
     * Sisältää tilausrivin (yhteen tilaukseen kuuluvat tuotteet, määrä ja hinta) datan
     */
    class TilausRivi {
        private int TILAUSRIVINUMERO;
        private int TILAUSNUMERO;
        private int TUOTENUMERO;
        private int MAARA;
        private int AHINTA;

        public int getTILAUSRIVINUMERO() {
            return TILAUSRIVINUMERO;
        }
        public int getTILAUSNUMERO() {
            return TILAUSNUMERO;
        }
        public int getTUOTENUMERO() {
            return TUOTENUMERO;
        }
        public int getMAARA() {
            return MAARA;
        }
        public int getAHINTA() {
            return AHINTA;
        }

        public TilausRivi(int tilausrivinumero, int tilausnumero, int tuotenumero, int maara, int ahinta) {
            this.TILAUSRIVINUMERO = tilausrivinumero;
            this.TILAUSNUMERO = tilausnumero;
            this.TUOTENUMERO = tuotenumero;
            this.MAARA = maara;
            this.AHINTA = ahinta;
        }
    }
    /**
     * Sisältää tilauksiin ja tilausriviin liittyvän datan
     */
    class TilausLista {
        private ArrayList<Tilaus> TILAUKSET;
        private ArrayList<TilausRivi> TILAUSRIVIT;

        public TilausLista() {
            this.TILAUKSET = new ArrayList<>();
            this.TILAUSRIVIT = new ArrayList<>();
        }
        /**
         *  Lisää tuotteita tilaukseen
         * @param tilausnumero
         * @param tuotenumero
         * @param maara
         * @param ahinta
         */
        public void lisaaTilausListaan(int tilausnumero, int tuotenumero, int maara, int ahinta) {
            String query = "INSERT INTO tilausrivi(`TILAUSNUMERO`, `TUOTENUMERO`, `MAARA`, `AHINTA`) ";
            query += "VALUES(?,?,?,?);";

            try {
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(query);
                st.setInt(1, tilausnumero);
                st.setInt(2, tuotenumero);
                st.setInt(3, maara);
                st.setInt(4, ahinta);
                suoritaTurvallinenSQLKysely(st, yhteys, "Lisätty", false);

                // TODO hae viimeisein tilausrivi id ja lisää se tähän
                query = "SELECT LAST_INSERT_ID() AS TILAUSRIVINUMERO";
                st = yhteys.prepareStatement(query);
                ResultSet rs = st.executeQuery();
                int tilausrivinro = 0;
                while (rs.next()) {
                    tilausrivinro = rs.getInt("TILAUSRIVINUMERO");
                }
                yhteys.close();

                DefaultTableModel model = (DefaultTableModel)jtblTilausrivi.getModel();
                int i = jtblTilausrivi.getSelectedRow();
                Object[] rowTlRivi = new Object[5];
                rowTlRivi[0] = tilausrivinro;
                rowTlRivi[1] = tilausnumero;
                rowTlRivi[2] = tuotenumero;
                rowTlRivi[3] = maara;
                rowTlRivi[4] = ahinta;
                model.addRow(rowTlRivi);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /**
         * Lisää uusi tilaus
         * @param tilausnumero
         * @param asiakasnumero
         * @param maksutapanumero
         * @param tilauspaiva
         * @param erapaiva
         * @param toimituspaiva
         * @param toimitustapa
         * @param lisatietoja
         */
        public void lisaaTilaus(int asiakasnumero, int maksutapanumero,
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

                suoritaTurvallinenSQLKysely(st, yhteys, "Lisätty", false);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /**
         * Päivitä tilausrivi (tilauksen tuotteet)
         * @param tilausrivinumero
         * @param tilausnumero
         * @param tuotenumero
         * @param maara
         * @param ahinta
         */
        public void paivitaTilausRivi(int tilausrivinumero, int tilausnumero, int tuotenumero, int maara, int ahinta) {
            Connection yhteys = luoYhteys();
            PreparedStatement st;
            String query = "UPDATE `tilausrivi` SET TUOTENUMERO=?, TILAUSNUMERO=?, MAARA=?, AHINTA=? WHERE TILAUSRIVINUMERO=?";
            try {
                st = yhteys.prepareStatement(query);
                st.setInt(1, tuotenumero);
                st.setInt(2, tilausnumero);
                st.setInt(3, maara);
                st.setInt(4, ahinta);
                st.setInt(5, tilausrivinumero);
                System.out.println(st.toString());
                suoritaTurvallinenSQLKysely(st, yhteys, "päivitetty", true);

                DefaultTableModel model = (DefaultTableModel)jtblTilausrivi.getModel();
                int i = jtblTilausrivi.getSelectedRow();
                model.setValueAt(tilausrivinumero, i, 0);
                model.setValueAt(tilausnumero, i, 1);
                model.setValueAt(tuotenumero, i, 2);
                model.setValueAt(maara, i, 3);
                model.setValueAt(ahinta, i, 4);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /**
         * Päivitä tilaus
         * @param tilausnumero
         * @param asiakasnumero
         * @param maksutapanumero
         * @param tilauspaiva
         * @param erapaiva
         * @param toimituspaiva
         * @param toimitustapa
         * @param lisatietoja
         */
        public void paivitaTilaus(int tilausnumero, int asiakasnumero, int maksutapanumero, String tilauspaiva, String erapaiva, String toimituspaiva, String toimitustapa, String lisatietoja) {
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
                suoritaTurvallinenSQLKysely(st, yhteys, "päivitetty", true);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /**
         * Etsi tilausrivi tilausrivi numerolla
         * @param tilausnro Tilausnumero
         * @return Palauttaa TilausRivi olion, Null jos tilausriviä ei löytynyt
         */
        public TilausRivi haeTilausriviTilausnumerolla(int tilausnro) {
            for (TilausRivi rivi : this.TILAUSRIVIT) {
                if (rivi.getTILAUSRIVINUMERO() == tilausnro) {
                    return rivi;
                }
            }
            return null;
        }
        public TilausRivi haeTilausrivi(int indeksi) {
            return this.TILAUSRIVIT.get(indeksi);
        }
        public int haeTilausriviPituus() {
            return this.TILAUSRIVIT.size();
        }
        public Tilaus haeTilaus(int indeksi) {
            return this.TILAUKSET.get(indeksi);
        }
        public int haeTilauslistaPituus() {
            return this.TILAUKSET.size();
        }
        /**
         * Poistaa tilausrivin
         * @param tilausriviNro Tilausrivinumero
         */
        public void poistaTilausRivi(int tilausriviNro) {
            //System.out.println("indeksi: " + tilausriviNro);
            String query = "DELETE FROM tilausrivi WHERE `TILAUSRIVINUMERO` = ?";

            try {
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(query);
                st.setInt(1, tilausriviNro);
                suoritaTurvallinenSQLKysely(st, yhteys, "Poistettu", true);

                DefaultTableModel model = (DefaultTableModel)jtblTilausrivi.getModel();
                int i = jtblTilausrivi.getSelectedRow();
                model.removeRow(i);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /**
         * Poistaa tilauksen mikäli tilauksessa ei ole tilausrivejä
         * @param tilausnumero Tilausnumero
         */
        public void poistaTilaus(int tilausnumero) {
            //System.out.println("indeksi: " + indeksi);
            //int tlNumero = this.TILAUKSET.get(indeksi).getTILAUSNUMERO();
            String query = "DELETE FROM tilaus WHERE `TILAUSNUMERO` = ?";

            try {
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(query);
                st.setInt(1, tilausnumero);
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
            this.TILAUSRIVIT.clear();

            String query = "SELECT ASIAKASNUMERO, TILAUSNUMERO, MAKSUTAPANUMERO, TILAUSPAIVA, ERAPAIVA, TOIMITUSPAIVA, TOIMITUSTAPA, LISATIETOJA FROM `tilaus` ";
            Statement st;
            ResultSet rs;
            try {
                st = yhteys.createStatement();
                rs = st.executeQuery(query);

                Tilaus tl;
                TilausRivi tlr;
                while (rs.next()) {
                    tl = new Tilaus(
                    rs.getInt("ASIAKASNUMERO"),
                    rs.getInt("TILAUSNUMERO"),
                    rs.getInt("MAKSUTAPANUMERO"),
                    rs.getDate("TILAUSPAIVA").toString(),
                    rs.getDate("ERAPAIVA").toString(),
                    rs.getDate("TOIMITUSPAIVA").toString(),
                    rs.getString("TOIMITUSTAPA"),
                    rs.getString("LISATIETOJA")
                    );
                    this.TILAUKSET.add(tl);
                }
                query = "SELECT TILAUSRIVINUMERO, TILAUSNUMERO, TUOTENUMERO, MAARA, AHINTA FROM `tilausrivi`";
                rs = st.executeQuery(query);
                while(rs.next()) {
                    tlr = new TilausRivi(
                        rs.getInt("TILAUSRIVINUMERO"),
                        rs.getInt("TILAUSNUMERO"),
                        rs.getInt("TUOTENUMERO"), rs.getInt("MAARA"),
                        rs.getInt("AHINTA"));
                        this.TILAUSRIVIT.add(tlr);
                }
                yhteys.close();
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
        tilausriviUIKayttoon(false);
    }
    /**
     * Tarkista onko kaikki tekstikentät täytetty oikein
     * @return
     */
    private boolean tarkistaTekstikentatTaytetty() {
        // TODO
        if (tilausriviValittu) {
            if (jtxtTilausnro.getText().equals("") || jtxtTuotenro.getText().equals("") ||
            jtxtHinta.getText().equals("") || jtxtHinta.getText().equals("") ) {
                return false;
            }
        }
        else {
            if (jtxtAsiakasnro.getText().equals("") || jtxtMaksutapa.getText().equals("")  || jtxtTilauspaiva.getText().equals("") || jtxtErapaiva.getText().equals("") ||
            jtxtToimituspaiva.getText().equals("") || jtxtToimitustapa.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
    /**
     * Päivittää tilausrivi taulukon
     */
    private void paivitaTilausriviTaulukko() {
        DefaultTableModel modelTlRivi = (DefaultTableModel)jtblTilausrivi.getModel();
        for (int i = jtblTilausrivi.getRowCount() -1; i >= 0; i--) {
            modelTlRivi.removeRow(i);
        }
        tilausLista.HaeTilauksetTietokannasta();
        int tilausnro = tilausLista.haeTilaus(jtblTilaukset.getSelectedRow()).getTILAUSNUMERO();
        for (int i = 0; i < tilausLista.haeTilausriviPituus(); i++) {
            TilausRivi tlr = tilausLista.haeTilausrivi(i);
            if (tilausnro == tlr.getTILAUSNUMERO()) {
                Object[] rowTlRivi = new Object[5];
                rowTlRivi[0] = tlr.getTILAUSRIVINUMERO();
                rowTlRivi[1] = tlr.getTILAUSNUMERO();
                rowTlRivi[2] = tlr.getTUOTENUMERO();
                rowTlRivi[3] = tlr.getMAARA();
                rowTlRivi[4] = tlr.getAHINTA();
                modelTlRivi.addRow(rowTlRivi);
            }
        }
    }
    /**
     * Päivittää tuotteet taulukon
     */
    private void paivitaTuotteetTaulukkoUI() {
        
        DefaultTableModel model = (DefaultTableModel)jtblTilaukset.getModel();
        // Poista entiset rivit
        for (int i = jtblTilaukset.getRowCount() -1; i >= 0; i--) {
            model.removeRow(i);
        }

        tilausLista.HaeTilauksetTietokannasta();

        for (int i = 0; i < tilausLista.haeTilauslistaPituus(); i++) {
            Object[] row = new Object[11];
            Tilaus tl = tilausLista.haeTilaus(i);
            row[0] = tl.getASIAKASNUMERO();
            row[1] = tl.getTILAUSNUMERO();
            row[2] = tl.getMAKSUTAPANUMERO();
            row[3] = tl.getTILAUSPAIVA();
            row[4] = tl.getERAPAIVA();
            row[5] = tl.getTOIMITUSPAIVA();
            row[6] = tl.getTOIMITUSTAPA();
            row[7] = tl.getLISATIETOJA();

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
                DefaultTableModel model = (DefaultTableModel)jtblTilaukset.getModel();
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

        btngrpTilaukset = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblTilaukset = new javax.swing.JTable();
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
        jLabel11 = new javax.swing.JLabel();
        jtxtTilausnro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblTilausrivi = new javax.swing.JTable();
        jrbtnUusitilaus = new javax.swing.JRadioButton();
        jrbtnLisaaTilaukseen = new javax.swing.JRadioButton();
        jtxtTilausrivinro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtxtMaksutapa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtblTilaukset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Asiakasnumero", "Tilausnumero", "Maksutapa", "Tilauspäivä", "Eräpäivä", "Toimituspäivä", "Toimitustapa", "Lisätietoja"
            }
        ));
        jtblTilaukset.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtblTilauksetFocusGained(evt);
            }
        });
        jtblTilaukset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTilauksetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblTilaukset);

        jLabel1.setText("Asiakasnumero:");

        jLabel2.setText("Tuotenumero:");

        jLabel3.setText("Määrä:");

        jLabel4.setText("A-hinta:");

        jtxtTilauspaiva.setToolTipText("Muodossa yyyy-MM-dd");

        jLabel5.setText("Tilauspäivä:");

        jLabel6.setText("Eräpäivä:");

        jtxtErapaiva.setToolTipText("Muodossa yyyy-MM-dd");

        jLabel7.setText("Toimituspäivä:");

        jtxtToimituspaiva.setToolTipText("Muodossa yyyy-MM-dd");

        jLabel8.setText("Toimitustapa:");

        jtxtToimitustapa.setToolTipText("");

        jLabel9.setText("Lisätietoja:");

        jtbnUusi.setText("Uusi tilaus");
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

        jtblTilausrivi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tilausrivinumero", "Tilausnumero", "Tuotenumero", "Määrä", "A'-Hinta"
            }
        ));
        jtblTilausrivi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtblTilausriviFocusGained(evt);
            }
        });
        jtblTilausrivi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblTilausriviMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtblTilausrivi);

        btngrpTilaukset.add(jrbtnUusitilaus);
        jrbtnUusitilaus.setText("Uusi tilaus");
        jrbtnUusitilaus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnUusitilausActionPerformed(evt);
            }
        });

        btngrpTilaukset.add(jrbtnLisaaTilaukseen);
        jrbtnLisaaTilaukseen.setText("Lisää tuote tilaukseen");
        jrbtnLisaaTilaukseen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnLisaaTilaukseenActionPerformed(evt);
            }
        });

        jLabel12.setText("Tilausrivinumero:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jtxtMaksutapa, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtTuotenro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtMaara, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtHinta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtErapaiva, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtToimituspaiva, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtToimitustapa, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtTilauspaiva, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtTilausnro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtLisatietoja, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxtAsiakasnro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jrbtnUusitilaus)
                                .addGap(29, 29, 29)
                                .addComponent(jrbtnLisaaTilaukseen))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtbnUusi)
                                .addGap(18, 18, 18)
                                .addComponent(jtbnPaivita)
                                .addGap(18, 18, 18)
                                .addComponent(jtbnPoista))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtTilausrivinro, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(jtxtHinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jtxtTilausrivinro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jtxtMaksutapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbtnLisaaTilaukseen)
                            .addComponent(jrbtnUusitilaus, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtbnUusi)
                            .addComponent(jtbnPaivita)
                            .addComponent(jtbnPoista))
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Tilaus valittu hiirellä
     * @param evt
     */
    private void jtblTilauksetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTilauksetMouseClicked
        int i = jtblTilaukset.getSelectedRow();
        Tilaus tl = tilausLista.haeTilaus(i);

        jtxtAsiakasnro.setText(String.valueOf(tl.getASIAKASNUMERO()));
        jtxtTilausnro.setText(String.valueOf(tl.getTILAUSNUMERO()));
        jtxtTilausnro.setEditable(false);
        jtxtMaksutapa.setText(String.valueOf(tl.getMAKSUTAPANUMERO()));
        jtxtTilauspaiva.setText(tl.getTILAUSPAIVA());
        jtxtErapaiva.setText(tl.getERAPAIVA());
        jtxtToimituspaiva.setText(tl.getTOIMITUSPAIVA());
        jtxtToimitustapa.setText(tl.getTOIMITUSTAPA());
        jtxtLisatietoja.setText(tl.getLISATIETOJA());

        paivitaTilausriviTaulukko();
    }//GEN-LAST:event_jtblTilauksetMouseClicked
    /**
     * Päivitä nappia painettu
     * @param evt
     */
    private void jtbnPaivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnPaivitaActionPerformed
       if (!tarkistaTekstikentatTaytetty()) {
            JOptionPane.showInternalMessageDialog(null, "Kaikki tekstikentät ei ole täytetty!", "", JOptionPane.ERROR_MESSAGE);
       }
       int i = jtblTilaukset.getSelectedRow();
       try {
            if(tilausriviValittu) {
                tilausLista.paivitaTilausRivi(
                Integer.parseInt(jtxtTilausrivinro.getText()),
                Integer.parseInt(jtxtTilausnro.getText()),
                Integer.parseInt(jtxtTuotenro.getText()),
                Integer.parseInt(jtxtMaara.getText()),
                Integer.parseInt(jtxtHinta.getText()));
            }
            else{
                tilausLista.paivitaTilaus(Integer.parseInt(jtxtTilausnro.getText()), Integer.parseInt(jtxtAsiakasnro.getText()),
                Integer.parseInt(jtxtMaksutapa.getText()),
                jtxtTilauspaiva.getText(), jtxtErapaiva.getText(), jtxtToimituspaiva.getText(), jtxtToimitustapa.getText(), jtxtLisatietoja.getText());
            }
        
       } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "Virhe tilauksen päivityksessä!", "", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jtbnPaivitaActionPerformed
    /**
     * Poista nappia painettu
     * @param evt
     */
    private void jtbnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnPoistaActionPerformed
        
        if (tilausriviValittu) {
            try {
                int tilausriviNro = Integer.valueOf(jtxtTilausrivinro.getText());
                tilausLista.poistaTilausRivi(tilausriviNro);
            } catch (Exception e) {
                JOptionPane.showInternalMessageDialog(null, "Virhe tilausrivin poistamisessa!", "", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        else {
            try {
                int tilausNro = Integer.valueOf(jtxtTilausnro.getText());
                tilausLista.poistaTilaus(tilausNro);
            } catch (Exception e) {
                JOptionPane.showInternalMessageDialog(null, "Virhe tilauksen poistamisessa!.", "", JOptionPane.ERROR_MESSAGE);
            }
            if (jtxtTilausnro.getText().equals("")) {
                
            }
            
        }
    }//GEN-LAST:event_jtbnPoistaActionPerformed
    /**
     * Uusi tilaus/tilausrivi nappia painettu
     * @param evt
     */
    private void jtbnUusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnUusiActionPerformed
        int tilausnro = 0;
        try {
            tilausnro = Integer.parseInt(jtxtTilausnro.getText());
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (tilausriviValittu) {
            tilausLista.lisaaTilausListaan(tilausnro, Integer.parseInt(jtxtTuotenro.getText()), Integer.parseInt(jtxtMaara.getText()), Integer.parseInt(jtxtHinta.getText()));
        }
        else  {
            tilausLista.lisaaTilaus(Integer.parseInt(jtxtAsiakasnro.getText()), Integer.parseInt(jtxtMaksutapa.getText()),
            jtxtTilauspaiva.getText(), jtxtErapaiva.getText(),
            jtxtToimituspaiva.getText(), jtxtToimitustapa.getText(), jtxtLisatietoja.getText());
        }
    }//GEN-LAST:event_jtbnUusiActionPerformed
    /**
     * Ottaa/Poistaa tilausrivi tekstikentät käyttöön/käytöstä ja tekee saman tilaus tekstikentille.
     * @param kaytossa
     */
    private void tilausriviUIKayttoon(boolean kaytossa) {
        tilausriviValittu = kaytossa;
        // tilaus UI
        jtxtAsiakasnro.setEnabled(!kaytossa);
        jtxtMaksutapa.setEnabled(!kaytossa);
        jtxtTilauspaiva.setEnabled(!kaytossa);
        jtxtErapaiva.setEnabled(!kaytossa);
        jtxtToimituspaiva.setEnabled(!kaytossa);
        jtxtToimitustapa.setEnabled(!kaytossa);
        jtxtLisatietoja.setEnabled(!kaytossa);

        // tilausrivi UI
        jtxtTuotenro.setEnabled(kaytossa);
        jtxtMaara.setEnabled(kaytossa);
        jtxtHinta.setEnabled(kaytossa);
        jtxtTilausrivinro.setEnabled(kaytossa);

        if(kaytossa) {
            jrbtnLisaaTilaukseen.setSelected(true);
            jtbnUusi.setText("Lisää tilaukseen");
        }
        else {
            jrbtnUusitilaus.setSelected(true);
            jtbnUusi.setText("Uusi tilaus");
        }
    }

    private boolean tilausriviValittu = false;
    private void jtblTilausriviFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtblTilausriviFocusGained
        tilausriviUIKayttoon(true);
    }//GEN-LAST:event_jtblTilausriviFocusGained
    private void jtblTilauksetFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtblTilauksetFocusGained
        tilausriviUIKayttoon(false);
    }//GEN-LAST:event_jtblTilauksetFocusGained
    /**
     * Tilausrivi taulukko valittu hiirellä
     * @param evt
     */
    private void jtblTilausriviMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblTilausriviMouseClicked
        int i = jtblTilausrivi.getSelectedRow();
        int tilausNro = (int)jtblTilausrivi.getValueAt(i, 0);
        TilausRivi tlr = tilausLista.haeTilausriviTilausnumerolla(tilausNro);
        jtxtTilausnro.setText(String.valueOf(tlr.getTILAUSNUMERO()));
        jtxtTuotenro.setText(String.valueOf(tlr.getTUOTENUMERO()));
        jtxtMaara.setText(String.valueOf(tlr.getMAARA()));
        jtxtHinta.setText(String.valueOf(tlr.getAHINTA()));
        jtxtTilausrivinro.setText(String.valueOf(tlr.getTILAUSRIVINUMERO()));
        jtxtTilausrivinro.setEditable(false);
    }//GEN-LAST:event_jtblTilausriviMouseClicked

    private void jrbtnLisaaTilaukseenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnLisaaTilaukseenActionPerformed
        tilausriviUIKayttoon(true);
    }//GEN-LAST:event_jrbtnLisaaTilaukseenActionPerformed

    private void jrbtnUusitilausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnUusitilausActionPerformed
        tilausriviUIKayttoon(false);
    }//GEN-LAST:event_jrbtnUusitilausActionPerformed

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
    private javax.swing.ButtonGroup btngrpTilaukset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton jrbtnLisaaTilaukseen;
    private javax.swing.JRadioButton jrbtnUusitilaus;
    private javax.swing.JTable jtblTilaukset;
    private javax.swing.JTable jtblTilausrivi;
    private javax.swing.JButton jtbnPaivita;
    private javax.swing.JButton jtbnPoista;
    private javax.swing.JButton jtbnUusi;
    private javax.swing.JTextField jtxtAsiakasnro;
    private javax.swing.JTextField jtxtErapaiva;
    private javax.swing.JTextField jtxtHinta;
    private javax.swing.JTextField jtxtLisatietoja;
    private javax.swing.JTextField jtxtMaara;
    private javax.swing.JTextField jtxtMaksutapa;
    private javax.swing.JTextField jtxtTilausnro;
    private javax.swing.JTextField jtxtTilauspaiva;
    private javax.swing.JTextField jtxtTilausrivinro;
    private javax.swing.JTextField jtxtToimituspaiva;
    private javax.swing.JTextField jtxtToimitustapa;
    private javax.swing.JTextField jtxtTuotenro;
    // End of variables declaration//GEN-END:variables
}
