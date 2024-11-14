/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jani.horoskooppi;

import java.time.LocalDate;

/**
 *
 * @author Jani
 */
public class Horoskooppi {
    private LocalDate _alkuAika;
    private LocalDate _loppuAika;
    private String _nimi;

    public Horoskooppi(String nimi, String alku, String loppu) {
        _alkuAika = LocalDate.parse(alku);
        _loppuAika = LocalDate.parse(loppu);
        _nimi = nimi;
    }

    public String getNimi() {
        return _nimi;
    }

    public Boolean onkoHoroskooppi(LocalDate paivamaara) {
        if (paivamaara.isAfter(_alkuAika) && paivamaara.isBefore(_loppuAika)
        || paivamaara.isEqual(_alkuAika) || paivamaara.isEqual(_loppuAika)) {
            return true;
        }
        else {
            return false;
        }
    }
}
