package org.example;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;

@Entity
@Table(name = "ov_chipkaart")
public class OvChipKaart {

    @Id
    @GeneratedValue
    @Column(name = "kaart_nummer")
    private int kaartNummer;

    @Column(name = "geldigtot")
    private Date geldigTot;

    @Column(name = "klasse")
    private int klasse;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Reiziger reiziger;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private ArrayList<Product> producten;

    public OvChipKaart(Date geldigTot, int klasse, double saldo, Reiziger reiziger, ArrayList<Product> producten) {
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        this.producten = producten;

        if (!reiziger.getOvChipKaarten().contains(this)) {
            reiziger.getOvChipKaarten().add(this);
        }
    }

    public OvChipKaart() {

    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Product> getProducten() {
        return producten;
    }

    public void setProducten(ArrayList<Product> producten) {
        this.producten = producten;
    }
}

