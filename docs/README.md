# OpenJPA P6 verslag

Pawel Kubosz, Justin Campell, Xavier Tromp


![Logo OpenJPA](openJPALogo.jpeg)


# Inhoudsopgave

- [Inhoudsopgave](#Inhoudsopgave)
- [Introductie](#Introductie)
- [Geschiedenis en fabrikanten](#Geschiedenis-en-fabrikanten)
- [Over elke fabrikant](#Over-elke-fabrikant)
  - [BEA Systems](#BEA-Systems)
  - [Apache Software Foundation](#Apache-Software-Foundation)
- [Wijze van gebruik](#Wijze-van-gebruik)
- [OpenJPA vergeleken met andere JPA's](#OpenJPA-vergeleken-met-andere-JPAs)
  - [Manier van installatie en configuratie](#Manier-van-installatie-en-configuratie)
  - [Populariteit](#Populariteit)
  - [Documentatie](#Documentatie)
  - [Voordelen ten opzichte van andere JPA's](#Voordelen-ten-opzichte-van-andere-JPAs)
  - [Nadelen ten opzichte van Hibernate](#Nadelen-ten-opzichte-van-Hibernate)

  
# Introductie
OpenJPA is een open source-implementatie van de Java Persistence API-specificatie. Het is een object-relationele mapping (ORM)-oplossing voor de Java-taal, die het opslaan van objecten in databases vereenvoudigt. 
Het is open-source software die wordt gedistribueerd onder de Apache-licentie 2.0.

OpenJPA is een open source-implementatie van de Java JPA-specificatie (Java Persistence API) van Apache. 
JPA biedt een agnostische op Java gebaseerde API voor het opslaan en ophalen van informatie in een backend-database. 
Het heeft een canonieke querytaal genaamd Java Persistence Query Language, of JPQL, die past bij de programmeermethoden van Java en de noodzaak elimineert om databasequery's op maat te maken voor een bepaalde database. 
JPA ondersteunt echter ook native SQL die kan worden gebruikt voor snelle poorten met een bekende backend-database. 
Deze tutorial is bedoeld om u door de stappen te leiden van het opzetten van een eenvoudige webapplicatie om OpenJPA Geronimo te gebruiken en om transacties uit te voeren met de Derby-database die bij Geronimo wordt geleverd. 
De tutorialcode maakt gebruik van een eenvoudige Java Server Page (JSP), ondersteund door enkele basisklassen. Het toont een tabel met inventarisitems en categorieën. 
In deze tutorial gaan we niet in op details met betrekking tot de JSP-code. 
Het doel ervan is om een venster te zijn waardoor u OpenJPA kunt onderzoeken. 
Het beoogde publiek voor deze tutorial bestaat uit mensen met enige kennis en begrip van de Java-programmeertaal en die net beginnen met OpenJPA.

## Geschiedenis en fabrikanten
In 2005 hebben BEA Systems de Kodo geëxpandeerd om een implementatie te zijn van beide JDO, als de JPA. 
Kodo is een Java Data Objects implementatie die oorspronkelijk ontwikkeld werd door SolarMetric in 2001. 
Zij werden ook in 2005 gekocht door BEA Systems. 
In 2006 werd een groot stuk van de Kodo source code aan de Apache Software Foundation gedoneerd onder de naam van OpenJPA.

## Over elke fabrikant

### BEA Systems
BEA systems Inc. Was een merk dat zich specialiseerde in de infrastructuur van softwareproducten. 
In 2008 was het overgenomen door Oracle Corporation.

### Apache Software Foundation
De Apache Software Foundation is een amerikaanse non-profitorganisatie die zich bezighoudt met de ontwikkeling en ondersteuning van open-source softwareprojecten. 
Opgericht in 1999, heeft de Apache Software Foundation (ASF) als doel om hoogwaardige, community-gedreven softwareprojecten te bevorderen en te verspreiden.

## Wijze van gebruik
OpenJPA opereert op een query taal die Java Persistence Query Language heet. Of JPQL in het kort. 
Het is een java gebaseerde API voor het opslaan en ophalen van informatie voor het backend. 

Zo kan je het openJPA in intellij gebruiken:

Eerst moet je het openJPA dependency toevoegen aan jouw project met behulp van het pom.xml als je maven gebruikt. Dit is de dependency die je moet toevoegen:

``` xml
<dependencies>

  <dependency>

    <groupId>org.apache.openjpa</groupId>

    <artifactId>openjpa</artifactId>

    <version>3.2.2</version> <!-- De versie kan variëren afhankelijk van de beschikbare versies -->

  </dependency>

</dependencies>
```

Daarna moet je een persistence.xml aanmaken in je project in de ‘src/main/recourses/META-INF’ directory. 
Dit bestand wordt gebruikt om de persistentie-eenheid te configureren.

Zo kan een persistence.xml bestand er uit zien:
``` xml

<?xml version="1.0" encoding="UTF-8"?>

<persistence xmlns="http://java.sun.com/xml/ns/persistence"

    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence\_2\_0.xsd"

    version="2.0">



<persistence-unit name="myPU">

    <provider>org.apache.openjpa.persistence.PersistenceProviderImpl</provider>
    
        <!-- Voeg hier je entiteitsklassen toe, hieronder staan de klasses van het ovChipKaart casus: -->
        
        <class>org.example.Reiziger</class>
        
        <class>org.example.Adres</class>
        
        <class>org.example.OvChipKaart</class>
        
        <class>org.example.Product</class>
        
        <properties>
        
            <!-- Database-verbindingseigenschappen -->
            
            
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
            
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/ovchip"/>
            
            <property name="javax.persistence.jdbc.user" value="postgres"/>
            
            <property name="javax.persistence.jdbc.password" value="99vm83ks"/>
            
            
            
            <!-- OpenJPA-specifieke eigenschappen -->
            
            <property name="openjpa.ConnectionFactoryName" value="jndi/MyDataSource"/>
            
            <property name="openjpa.Log" value="DefaultLevel=WARN, Runtime=INFO, Tool=INFO, SQL=TRACE"/>
            
            
            
            <!-- Andere JPA-configuratie-opties -->
            
            <property name="openjpa.RuntimeUnenhancedClasses" value="supported" />
            
            <property name="openjpa.jdbc.SynchronizeMappings" value="buildSchema(ForeignKeys=true)" />
            
            
            
        </properties>
    
    </persistence-unit>

</persistence>
```


Na deze stappen kan je het openJPA in je java code gerbuiken. 
Je kan entiteiten aanmaken en er operaties op uitvoeren met het gebruik van de ‘EntityManager’

In de onderstaande voorbeeldcode moet 'myPersistenceUnit' overeenkomen met de naam van de persistentie-eenheid die is opgegeven in je persistence.xml-bestand. 
Hieronder staat hoe je de CRUD-functies gebruikt met openJPA: 

``` Java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
EntityManager em = emf.createEntityManager();

EntityTransaction tx =  em.getTransaction();
tx.begin();


em.persist(reiziger);

em.persist(reiziger2);

Reiziger foundReiziger = em.find(Reiziger.class, 101);
System.out.println(foundReiziger);

Reiziger reizigerToUpdate = em.find(Reiziger.class, 101);
reizigerToUpdate.setGeboortedatum(Date.valueOf("2000-06-21"));
reizigerToUpdate.setTussenvoegsel("van den");
em.merge(reizigerToUpdate);
System.out.println(reizigerToUpdate);
tx.commit();

Reiziger toDelete = em.find(Reiziger.class, reiziger2.getReizerId());
System.out.println(toDelete);
em.remove(toDelete);
tx.commit();

em.close();
emf.close();
```
#



# OpenJPA vergeleken met andere JPA's
###
## Manier van installatie en configuratie
OpenJPA wordt net zoals bijvoorbeeld Hibernate & Eclips Link in een project gezet met behulp van een jar bestand. 
Hiernaast kan je in openJPA configuraties toevoegen met behulp van xml.

## Populariteit
OpenJPA is helemaal niet populair en heeft een kleine community. 
Het heeft maar een marktaandeel van 0.05%. Ook wordt de site zelden geupdated.

![markt percentage OpenJPA](openJPAMarketShare.png)
###
## Documentatie
Het is lastig om bronnen en informatie te vinden over openJPA, omdat het zo weinig gebruikt wordt. 
Op Youtube is er bijna niks over te vinden en maar weinig websites behandelen OpenJPA, zo ook Stack Overflow. 
De enige website die uitleg geeft over openJPA, is de website van openJPA zelf. 
Als je dit vergelijkt met Hibernate of Eclips Link is openJPA een kleine speler.

## Voordelen ten opzichte van andere JPA's

- **Eenvoudig te integreren met Appache-softwarestack:** 
OpenJPA is van Appache en is hierdoor makkelijk te integreren met Appache-softwarestacks.

- **Modulaire structuur:** 
OpenJPA heeft een modulair structuur, wat betekent dat het uit verschillende componenten bestaat die onafhankelijk kunnen gebruikt worden. 
Hierdoor kun je ervoor kiezen om alleen gedeeltes van de bibliotheek te kunnen gebruiken die je nodig hebt. 
Hierdoor heb je in je project niet ongebruikte functies zitten. 
Hibernate heeft een monolithische structuur, waardoor je soms meer opneemt in je project dan dat je eigenlijk nodig hebt.

- **Snellere prestaties:** 
Vanwege de modulaire structuur, kan OpenJPA sneller en efficiënter werken dan Hibernate.

## Nadelen ten opzichte van Hibernate

- Weinig bronnen: Er is niet veel informatie te vinden over OpenJPA, omdat het weinig gebruikt wordt. 
Hierom is het dus lastig OpenJPA te leren en om oplossingen te kunnen vinden bij bepaalde problemen, in tegendeel tot Hibernate & EclipseLink waarvan er genoeg informatie over te vinden is.
- Minder integratie met moderne technologieën: Hibernate heeft zich aangepast aan het ondersteunen van moderne technologieën zoals bijvoorbeeld microservices integendeel tot OpenJPA, dat vanwege de kleine community niet vaak geupdated wordt.



