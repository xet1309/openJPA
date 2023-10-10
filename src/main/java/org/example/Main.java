package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        ArrayList<Product> products = new ArrayList<>();
        ArrayList<OvChipKaart> ovChipKaarts = new ArrayList<>();

        Reiziger reiziger = new Reiziger("J.", "van der", "Doe", Date.valueOf(LocalDate.now()));

        Adres adres = new Adres("1234 AB", "1A", "Straatnaam", "Woonplaats", reiziger);

        Product product1 = new Product("Product 1", "Dit is product 1", 9.99, ovChipKaarts);
        products.add(product1);
        Product product2 = new Product("Product 2", "Dit is product 2", 14.99, ovChipKaarts);
        products.add(product2);
        Product product3 = new Product("Product 3", "Dit is product 3", 7.49, ovChipKaarts);
        products.add(product3);

        OvChipKaart ovChipKaart1 = new OvChipKaart(Date.valueOf(LocalDate.now()), 2, 20.0, reiziger, products);
        ovChipKaarts.add(ovChipKaart1);
        OvChipKaart ovChipKaart2 = new OvChipKaart(Date.valueOf(LocalDate.now()), 1, 15.0, reiziger, products);
        ovChipKaarts.add(ovChipKaart2);
        OvChipKaart ovChipKaart3 = new OvChipKaart(Date.valueOf(LocalDate.now()), 2, 18.5, reiziger, products);
        ovChipKaarts.add(ovChipKaart3);

        em.persist(reiziger);
        tx.commit();
    }
}