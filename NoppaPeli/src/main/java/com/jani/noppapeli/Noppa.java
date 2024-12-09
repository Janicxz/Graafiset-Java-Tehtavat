/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jani.noppapeli;

import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Jani
 */
public class Noppa {
    private int LUKU;
    private Random RAND;
    private ImageIcon KUVA;
    public Noppa() {
        this.RAND = new Random();
    }
    public int heita() {
        this.LUKU = this.RAND.nextInt(6) + 1;
        return this.LUKU;
    }
    public int haeLuku() {
        return this.LUKU;
    }
    public void setluku(int luku) {
        this.LUKU = luku;
    }
    public ImageIcon haeKuva() {
        this.KUVA = new ImageIcon(String.valueOf(this.LUKU) + "_dots.png");
        return this.KUVA;
    }
}
