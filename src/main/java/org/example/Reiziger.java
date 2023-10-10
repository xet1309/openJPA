package org.example;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {

    @Id
    @GeneratedValue
    @Column(name = "reiziger_id")
    private int reizerId;

    @Column(name = "voorletters")
    private String voorletters;

    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;

    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "geboortedatum")
    private Date geboortedatum;


    @OneToOne(cascade = CascadeType.PERSIST)
    @Column(name = "adres_id")
    private Adres adres;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OvChipKaart> ovChipKaarten = new ArrayList<>();

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger() {

    }

    public int getReizerId() {
        return reizerId;
    }

    public void setReizerId(int reizerId) {
        this.reizerId = reizerId;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {

        if (tussenvoegsel == null) {
            return "";
        } else {
            return tussenvoegsel;
        }
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OvChipKaart> getOvChipKaarten() {
        return ovChipKaarten;
    }

    public void setOvChipKaarten(List<OvChipKaart> ovChipKaarten) {
        this.ovChipKaarten = ovChipKaarten;
    }

    public String toString() {
        if (tussenvoegsel == null || tussenvoegsel.equals("")) {
            return ("#" + reizerId + ": " + voorletters + "." + achternaam + ", Geboorte-Datum: (" + geboortedatum + ")");

        } else {
            return ("#" + reizerId + ": " + voorletters + ". " + tussenvoegsel + " " + achternaam + ", Geboorte-Datum: (" + geboortedatum + ")");

        }

    }
}

