package org.example;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_nummer")
    private int productNummer;

    @Column(name = "naam")
    private String naam;

    @Column(name = "beschrijving")
    private String beschrijving;

    @Column(name = "prijs")
    private double prijs;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private ArrayList<OvChipKaart> ovChipKaarten;

    public Product(String naam, String beschrijving, double prijs, ArrayList<OvChipKaart> ovChipKaarten) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipKaarten = ovChipKaarten;
    }

    public Product() {

    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public ArrayList<OvChipKaart> getOvChipKaarten() {
        return ovChipKaarten;
    }

    public void setOvChipKaarten(ArrayList<OvChipKaart> ovChipKaarten) {
        this.ovChipKaarten = ovChipKaarten;
    }

    public String toString() {
        return "#" + productNummer + " - Naam: " + naam + " - Prijs: " + prijs + " - Beschrijving: " + beschrijving;
    }

}

