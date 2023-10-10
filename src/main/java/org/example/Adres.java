package org.example;

import javax.persistence.*;

@Entity()
@Table(name = "adres")
public class Adres {

    @Id
    @GeneratedValue()
    @Column(name = "adres_id")
    private int adresId;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "huisnummer")
    private String huisnummer;

    @Column(name = "straat")
    private String straat;

    @Column(name = "woonplaats")
    private String woonplaats;

    @Column(name = "reiziger_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Reiziger reiziger;

    public Adres(String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger = reiziger;

        reiziger.setAdres(this);

    }

    public Adres() {

    }

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat=straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public String toString() {
        return reiziger.getAchternaam() + " woont op: #" + adresId + " - " + postcode + " - " + straat + "  " + huisnummer + " - " + woonplaats;
    }
}

