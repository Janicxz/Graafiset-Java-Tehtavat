/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jani.parturihinnoittelu;

import java.util.ArrayList;

/**
 *
 * @author Jani
 */
public class TilaustenHallinta {
    private ArrayList<Tilaus> TILAUKSET;
    
    public TilaustenHallinta() {
        this.TILAUKSET = new ArrayList<>();
    }
    
    public void lisaaTilaus(String nimi, double hinta, int maara) {
        this.TILAUKSET.add(new Tilaus(nimi, hinta, maara));
    }
    public void poistaTilaus(int indeksi) {
        this.TILAUKSET.remove(indeksi);
    }
    public void poistaKaikkiTilaukset() {
        this.TILAUKSET.clear();
    }
    public Tilaus haeTilaus(int indeksi) {
        return this.TILAUKSET.get(indeksi);
    }
    public int haeTilauslistaPituus() {
        return this.TILAUKSET.size();
    }
}
