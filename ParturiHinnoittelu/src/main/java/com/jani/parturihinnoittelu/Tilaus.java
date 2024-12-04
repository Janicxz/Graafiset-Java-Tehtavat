/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jani.parturihinnoittelu;

/**
 *
 * @author Jani
 */
public class Tilaus {
    private double HINTA;
    private int MAARA;
    private String NIMI;
    
    public double haeHinta() {
        return this.HINTA;
    }
    public int haeMaara() {
        return this.MAARA;
    }
    public String haeNimi() {
        return this.NIMI;
    }
    public double haeHintaSumma() {
        return this.HINTA * this.MAARA;
    }
    
    public Tilaus(String nimi, double hinta, int maara) {
        this.NIMI = nimi;
        this.HINTA = hinta;
        this.MAARA = maara;
    }
}
