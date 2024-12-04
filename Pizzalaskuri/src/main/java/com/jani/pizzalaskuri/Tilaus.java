/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jani.pizzalaskuri;

import java.util.ArrayList;

/**
 *
 * @author Jani
 */
public class Tilaus {
    private String NIMI;
    private double HINTA;
    private int MAARA;
    
    public double haeHinta() {
        return this.HINTA;
    }
    public String haeNimi() {
        return this.NIMI;
    }
    public int haeMaara() {
        return this.MAARA;
    }
    public double haeHintaYhteensa() {
        return this.HINTA * this.MAARA;
    }
    
    public Tilaus(String nimi, double hinta, int maara) {
        this.HINTA = hinta;
        this.MAARA = maara;
        this.NIMI = nimi;
    }   
}