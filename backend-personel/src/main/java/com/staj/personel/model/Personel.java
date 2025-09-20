package com.staj.personel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;
    private String email;
    private String pozisyon;

    public Personel() {
    }

    public Personel(String ad, String soyad, String email, String pozisyon) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.pozisyon = pozisyon;
    }

    // Getter - Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPozisyon() { return pozisyon; }
    public void setPozisyon(String pozisyon) { this.pozisyon = pozisyon; }
}
