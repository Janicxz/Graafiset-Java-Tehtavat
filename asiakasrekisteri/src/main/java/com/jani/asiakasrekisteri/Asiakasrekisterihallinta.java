/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jani.asiakasrekisteri;

import java.sql.Connection;
import java.sql.*;
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
//import javax.swing.table.TableModel;

/**
 *
 * @author Jani
 */
public class Asiakasrekisterihallinta extends javax.swing.JFrame {
    /**
     * Asiakas luokka, käytetään Asiakaslistan täyttämisessä
     * Sisältää kaikki asiakkaan liittyvät tiedot joita käsitellään asiakastietokannassa
     */
    class Asiakas {
        private int ASIAKASNUMERO;
        private String ASIAKKAAKSITULOPAIVA;
        private String ETUNIMI;
        private String SUKUNIMI;
        private String YRITYS;
        private String KATUOSOITE;
        private String POSTINUMERO;
        private String POSTITOIMIPAIKKA;
        private String PUHELIN;
        private String EMAIL;

        public Asiakas(int id, String asiakkaaksiTulopaiva, String etunimi, String sukunimi, String yritys, String katuosoite, String postinumero, String postitoimipaikka, String puhelin, String email) {
            this.ASIAKASNUMERO = id;
            this.ASIAKKAAKSITULOPAIVA = asiakkaaksiTulopaiva;
            this.ETUNIMI = etunimi;
            this.SUKUNIMI = sukunimi;
            this.YRITYS = yritys;
            this.KATUOSOITE = katuosoite;
            this.POSTINUMERO = postinumero;
            this.POSTITOIMIPAIKKA = postitoimipaikka;
            this.PUHELIN = puhelin;
            this.EMAIL = email;
        }
        /**
         * Päivittää asiakkaan tiedot tietokantaan
         * @param asiakkaaksiTulopaiva Annetaan muodossa yyyy-mm-dd
         * @param etunimi
         * @param sukunimi
         * @param yritys
         * @param katuosoite
         * @param postinumero
         * @param postitoimipaikka
         * @param puhelin
         * @param email
         */
        public void PaivitaAsiakasTiedot(String asiakkaaksiTulopaiva, String etunimi, String sukunimi, String yritys, String katuosoite, String postinumero, String postitoimipaikka, String puhelin, String email) {
            // Ei päivitetä tietoja mikäli tietokanta kysely epäonnistui
            if (!this._paivitaAsiakastiedotSQL()) {
                return;
            }
            
            this.ASIAKKAAKSITULOPAIVA = asiakkaaksiTulopaiva;
            this.ETUNIMI = etunimi;
            this.SUKUNIMI = sukunimi;
            this.YRITYS = yritys;
            this.KATUOSOITE = katuosoite;
            this.POSTINUMERO = postinumero;
            this.POSTITOIMIPAIKKA = postitoimipaikka;
            this.PUHELIN = puhelin;
            this.EMAIL = email;
        }
        /**
         * Poistaa tämän asiakkaan tietokannasta
         */
        public void PoistaAsiakas() {
            try {
                String kysely = "DELETE FROM ASIAKAS WHERE ASIAKASNUMERO=?";
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(kysely);
                st.setString(1, String.valueOf(this.ASIAKASNUMERO));
                suoritaTurvallinenSQLKysely(st, yhteys, "poistettu");

            } catch (Exception e) {
                JOptionPane.showInternalMessageDialog(null, "Asiakkaan poistaminen epäonnistui");
                e.printStackTrace();
            }
        }
        /**
         * Päivittää tämän asiakkaan tiedot tietokantaan, palauttaa true jos onnistui, false muulloin.
         * @return True jos päivitys onnistui. False jos ei.
         */
        private boolean _paivitaAsiakastiedotSQL() {
            try {
                String kysely = "UPDATE ASIAKAS SET ETUNIMI=?, SUKUNIMI=?, YRITYS=?, KATUOSOITE=?, POSTINUMERO=?, POSTITOIMIPAIKKA=?" +
                ", PUHELIN=?, EMAIL=?, ASIAKKAAKSITULOPAIVA=? WHERE ASIAKASNUMERO=?";
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(kysely);
                st.setString(1, jtxtEtunimi.getText());
                st.setString(2, jtxtSukunimi.getText());
                st.setString(3, jtxtYritys.getText());
                st.setString(4, jtxtKatuosoite.getText());
                st.setString(5, jtxtPostinumero.getText());
                st.setString(6, jtxtPostitoimipaikka.getText());
                st.setString(7, jtxtPuhelin.getText());
                st.setString(8, jtxtEmail.getText());
                st.setString(9, jtxtAsiakkaaksitulopaiva.getText());
                st.setString(10, jtxtAsiakasnumero.getText());
                suoritaTurvallinenSQLKysely(st, yhteys, "päivitetty");
                return true;
            } catch (Exception e) {
                JOptionPane.showInternalMessageDialog(null, "Asiakkaan tietojen päivitys epäonnistui");
                e.printStackTrace();
                return false;
            }
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
        public String HaeAsiakkaaksitulopaiva() {
            return this.ASIAKKAAKSITULOPAIVA;
        }
    }
    /**
     * Lista asiakkaista haettuna Asiakastietokannasta
     */
    class AsiakasLista {
        private ArrayList<Asiakas> asiakkaat;

        public AsiakasLista() {
            this.asiakkaat = new ArrayList<>();
            this.HaeAsiakkaatTietokannasta();
        }
        /**
         * Tyhjennä nykyinen asiakas lista ja hae uusi lista tietokannasta
         */
        public void HaeAsiakkaatTietokannasta() {
            Connection yhteys = luoYhteys();
            this.asiakkaat.clear();

            String query = "SELECT ASIAKASNUMERO, ASIAKKAAKSITULOPAIVA, ETUNIMI, SUKUNIMI, YRITYS, KATUOSOITE, POSTINUMERO, POSTITOIMIPAIKKA, PUHELIN, EMAIL FROM ASIAKAS";
            Statement st;
            ResultSet rs;
            try {
                st = yhteys.createStatement();
                rs = st.executeQuery(query);
    
                Asiakas as;
                while (rs.next()) {
                    as = new Asiakas(
                    rs.getInt("ASIAKASNUMERO"),
                    rs.getDate("ASIAKKAAKSITULOPAIVA").toString(),
                    rs.getString("ETUNIMI"),
                    rs.getString("SUKUNIMI"),
                    rs.getString("YRITYS"),
                    rs.getString("KATUOSOITE"),
                    rs.getString("POSTINUMERO"),
                    rs.getString("POSTITOIMIPAIKKA"),
                    rs.getString("PUHELIN"),
                    rs.getString("EMAIL")
                    );
                    this.asiakkaat.add(as);
                }
            } catch (SQLException e) {
                System.out.println("Virhe asiakastaulukon haussa!");
                e.printStackTrace();
            }
        }
        /**
         * Hae asiakas taulukko ID:lla
         * @param taulukkoID
         * @return
         */
        public Asiakas HaeAsiakas(int taulukkoID) {
            return this.asiakkaat.get(taulukkoID);
        }
        /**
         * Palauttaa asiakaslistan koon
         * @return
         */
        public int AsiakasListanPituus() {
            return this.asiakkaat.size();
        }
        public void PoistaAsiakas(int taulukkoID) {
            this.asiakkaat.get(taulukkoID).PoistaAsiakas();
            this.asiakkaat.remove(taulukkoID);
        }
        /**
         * Lisää uusi asiakas, lisää asiakkaan tiedot samalla asiakastietokantaan
         * @param asiakkaaksiTulopaiva Asiakkaaksitulopäivä muodossa yyyy-mm-dd
         */
        public void LisaaAsiakas(String asiakkaaksiTulopaiva, String yritys, String etunimi, String sukunimi, String katuosoite, String postinumero, String postitoimipaikka, String puhelin, String email) {
            try {
                String kysely = "INSERT INTO `ASIAKAS`(`ASIAKKAAKSITULOPAIVA`, `YRITYS`, `ETUNIMI`, `SUKUNIMI`, `KATUOSOITE`, `POSTINUMERO`, `POSTITOIMIPAIKKA`, `PUHELIN`, `EMAIL`) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
                Connection yhteys = luoYhteys();
                PreparedStatement st = yhteys.prepareStatement(kysely);

                st.setDate(1, java.sql.Date.valueOf(asiakkaaksiTulopaiva));
                st.setString(2, yritys);
                st.setString(3, etunimi);
                st.setString(4, sukunimi);
                st.setString(5, katuosoite);
                st.setString(6, postinumero);
                st.setString(7, postitoimipaikka);
                st.setString(8, puhelin);
                st.setString(9, email);

                suoritaTurvallinenSQLKysely(st, yhteys, "lisätty");
                this.HaeAsiakkaatTietokannasta();
            } catch (Exception e) {
                JOptionPane.showInternalMessageDialog(null, "Asiakkaan lisäys epäonnistui");
                e.printStackTrace();
            }
        }
        
    }

    AsiakasLista asiakasLista;
    /**
     * Creates new form Asiakasrekisterihallinta
     */
    public Asiakasrekisterihallinta() {
        initComponents();
        // Lataa asiakastiedot tietokannasta
        asiakasLista = new AsiakasLista();
        NaytaAsiakkaat();
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
     * Yhdistää asiakastietokantaan ja palauttaa Asiakastaulukon, ei palauta mitään virheen sattuessa
     * @return Palauttaa ArrayList<Asiakas> taulukon asiakastietokannasta
     */
    public ArrayList<Asiakas> HaeAsiakasTaulukko() {
        ArrayList<Asiakas> Asiakastaulukko = new ArrayList<Asiakas>();

        Connection yhteys = luoYhteys();

        String query = "SELECT ASIAKASNUMERO, ASIAKKAAKSITULOPAIVA, ETUNIMI, SUKUNIMI, YRITYS, KATUOSOITE, POSTINUMERO, POSTITOIMIPAIKKA, PUHELIN, EMAIL FROM ASIAKAS";
        Statement st;
        ResultSet rs;
        try {
            st = yhteys.createStatement();
            rs = st.executeQuery(query);

            Asiakas as;
            while (rs.next()) {
                as = new Asiakas(
                rs.getInt("ASIAKASNUMERO"),
                rs.getDate("ASIAKKAAKSITULOPAIVA").toString(),
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
    /**
     * Hakee uuden asiakaslistan tietokannasta ja täyttää asiakastaulukon
     */
    public void NaytaAsiakkaat() {
        DefaultTableModel model = (DefaultTableModel)jtblAsiakkaat.getModel();
        Object[] row = new Object[10];
        // Poista entiset rivit
        for (int i = jtblAsiakkaat.getRowCount() -1; i >= 0; i--) {
            model.removeRow(i);
        }
        // Lisää uudet asiakkaat
        asiakasLista.HaeAsiakkaatTietokannasta();
        for (int i = 0; i < asiakasLista.AsiakasListanPituus(); i++) {
            row[0] = asiakasLista.HaeAsiakas(i).HaeAsiakasnumero();
            row[1] = asiakasLista.HaeAsiakas(i).HaeAsiakkaaksitulopaiva();
            row[2] = asiakasLista.HaeAsiakas(i).HaeEtunimi();
            row[3] = asiakasLista.HaeAsiakas(i).HaeSukunimi();
            row[4] = asiakasLista.HaeAsiakas(i).HaeYritys();
            row[5] = asiakasLista.HaeAsiakas(i).HaeKatuosoite();
            row[6] = asiakasLista.HaeAsiakas(i).HaePostinumero();
            row[7] = asiakasLista.HaeAsiakas(i).HaePostitoimipaikka();
            row[8] = asiakasLista.HaeAsiakas(i).HaePuhelin();
            row[9] = asiakasLista.HaeAsiakas(i).HaeEmail();
            model.addRow(row);
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
                DefaultTableModel model = (DefaultTableModel)jtblAsiakkaat.getModel();
                model.setRowCount(0);
                NaytaAsiakkaat();

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
     * Suorittaa annetun kyselyn ja näyttää viestin käyttäjälle
     * @param query suoritettava SQL-kysely
     * @param message käyttäjälle näytettävä viesti kyselyn jälkeen
     */
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
    /**
     * Tarkistaa onko kaikki asiakas tekstikentät täytetty
     * @return Palauttaa true mikäli asiakastiedot täytetty asianmukaisesti, false muulloin.
     */
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
        jtxtAsiakkaaksitulopaiva = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asiakasrekisteri");

        jLabel1.setText("Asiakasnumero:");

        jLabel2.setText("Etunimi:");

        jLabel3.setText("Sukunimi:");

        jLabel4.setText("Yritys:");

        jtblAsiakkaat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Asiakasnumero", "Asiakkaaksitulopäivä", "Etunimi", "Sukunimi", "Yritys", "Katuosoite", "Postinumero", "Postitoimipaikka", "Puhelin", "Email"
            }
        ));
        jtblAsiakkaat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAsiakkaatMouseClicked(evt);
            }
        });
        jtblAsiakkaat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtblAsiakkaatKeyPressed(evt);
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

        jtxtAsiakkaaksitulopaiva.setToolTipText("Muodossa yyyy-mm-dd");

        jLabel10.setText("Asiakkaaksitulopäivä:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnUusi)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnPaivita)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnPoista)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))
                                .addGap(53, 53, 53))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(69, 69, 69)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtPuhelin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtPostitoimipaikka, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtPostinumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtKatuosoite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtxtSukunimi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtEtunimi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtYritys, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxtAsiakkaaksitulopaiva, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtAsiakasnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtAsiakkaaksitulopaiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtEtunimi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtPuhelin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnUusi)
                            .addComponent(jbtnPaivita)
                            .addComponent(jbtnPoista)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(88, 88, 88))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * 
     */
    private void paivitaValittuAsiakasTaulukkoUI() {
        int i = jtblAsiakkaat.getSelectedRow();

        jtxtAsiakasnumero.setText(String.valueOf(asiakasLista.HaeAsiakas(i).HaeAsiakasnumero()));
        jtxtAsiakkaaksitulopaiva.setText(asiakasLista.HaeAsiakas(i).HaeAsiakkaaksitulopaiva());
        jtxtEtunimi.setText(asiakasLista.HaeAsiakas(i).HaeEtunimi());
        jtxtSukunimi.setText(asiakasLista.HaeAsiakas(i).HaeSukunimi());
        jtxtYritys.setText(asiakasLista.HaeAsiakas(i).HaeYritys());
        jtxtKatuosoite.setText(asiakasLista.HaeAsiakas(i).HaeKatuosoite());
        jtxtPostinumero.setText(asiakasLista.HaeAsiakas(i).HaePostinumero());
        jtxtPostitoimipaikka.setText(asiakasLista.HaeAsiakas(i).HaePostitoimipaikka());
        jtxtPuhelin.setText(asiakasLista.HaeAsiakas(i).HaePuhelin());
        jtxtEmail.setText(asiakasLista.HaeAsiakas(i).HaeEmail());
    }

    private void jtblAsiakkaatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAsiakkaatMouseClicked
        // Päivitä käyttöliittymään valitun asiakkaan tiedot
        paivitaValittuAsiakasTaulukkoUI();
    }//GEN-LAST:event_jtblAsiakkaatMouseClicked

    private void jbtnUusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUusiActionPerformed
        // Tarkista onko asiakkaan tiedot täytetty oikein
        if (!onkoAsiakastiedotTaytetty()) {
            JOptionPane.showInternalMessageDialog(null, "Virhe lisättäessä uutta asiakasta!\n Jokin tekstikenttä on tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lisää uusi asiakas
        if (JOptionPane.showConfirmDialog(null, "Haluatko lisätä uuden asiakkaan "  + jtxtEtunimi.getText() + " " + jtxtSukunimi.getText() + "?", "Lisää", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tamaPaiva = new Date();
            // Jos annettu asiakkaaksitulopäivä, käytä sitä tämän päivän sijaan.
            if (!jtxtAsiakkaaksitulopaiva.getText().equals("")){
                try {
                    tamaPaiva = dateFormat.parse(jtxtAsiakkaaksitulopaiva.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            asiakasLista.LisaaAsiakas(dateFormat.format(tamaPaiva), jtxtYritys.getText(),
            jtxtEtunimi.getText(), jtxtSukunimi.getText(), jtxtKatuosoite.getText(),
            jtxtPostinumero.getText(), jtxtPostitoimipaikka.getText(),
            jtxtPuhelin.getText(), jtxtEmail.getText());
        }
    }//GEN-LAST:event_jbtnUusiActionPerformed

    private void jbtnPaivitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPaivitaActionPerformed
        // Tarkista onko asiakkaan tiedot täytetty oikein
        if (!onkoAsiakastiedotTaytetty()) {
            JOptionPane.showInternalMessageDialog(null, "Virhe päivitettäessä asiakkaan tietoja!\n Jokin tekstikenttä on tyhjä!", "Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Päivitä asiakkaan tiedot
        if (JOptionPane.showConfirmDialog(null, "Haluatko päivittää asiakkaan " + "(" + jtxtAsiakasnumero.getText() + ") "  + jtxtEtunimi.getText() + " " + jtxtSukunimi.getText() + " tiedot?", "Päivitä", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            int i = jtblAsiakkaat.getSelectedRow();
            asiakasLista.HaeAsiakas(i).PaivitaAsiakasTiedot(jtxtAsiakkaaksitulopaiva.getText(),
            jtxtEtunimi.getText(), jtxtSukunimi.getText(), jtxtYritys.getText(),
            jtxtKatuosoite.getText(), jtxtPostinumero.getText(), jtxtPostitoimipaikka.getText(),
            jtxtPuhelin.getText(), jtxtEmail.getText());
        }
    }//GEN-LAST:event_jbtnPaivitaActionPerformed

    private void jbtnPoistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPoistaActionPerformed
        // Poista valittu asiakas tietokannasta.
        if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistaa asiakkaan " + "(" + jtxtAsiakasnumero.getText() + ") " + jtxtEtunimi.getText() + " " + jtxtSukunimi.getText() + "?", "Poista", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            int i = jtblAsiakkaat.getSelectedRow();
            asiakasLista.HaeAsiakas(i).PoistaAsiakas();
        }

    }//GEN-LAST:event_jbtnPoistaActionPerformed

    private void jtxtPostinumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPostinumeroActionPerformed

    }//GEN-LAST:event_jtxtPostinumeroActionPerformed

    private void jtblAsiakkaatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblAsiakkaatKeyPressed
        // TODO: 
        /*if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP || evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            paivitaValittuAsiakasTaulukkoUI();
        }*/
    }//GEN-LAST:event_jtblAsiakkaatKeyPressed

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
    private javax.swing.JButton jbtnPaivita;
    private javax.swing.JButton jbtnPoista;
    private javax.swing.JButton jbtnUusi;
    private javax.swing.JTable jtblAsiakkaat;
    private javax.swing.JTextField jtxtAsiakasnumero;
    private javax.swing.JTextField jtxtAsiakkaaksitulopaiva;
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
