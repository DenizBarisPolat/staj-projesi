package com.staj.notification.messaging.dto;

import java.time.Instant;

public class NotificationMessage {
    private String event; 
    private Long id;

    private String ad;
    private String soyad;
    private String email;
    private String pozisyon;

    private String oldAd;
    private String oldSoyad;
    private String oldEmail;
    private String oldPozisyon;

    private Instant timestamp;

    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

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

    public String getOldAd() { return oldAd; }
    public void setOldAd(String oldAd) { this.oldAd = oldAd; }

    public String getOldSoyad() { return oldSoyad; }
    public void setOldSoyad(String oldSoyad) { this.oldSoyad = oldSoyad; }

    public String getOldEmail() { return oldEmail; }
    public void setOldEmail(String oldEmail) { this.oldEmail = oldEmail; }

    public String getOldPozisyon() { return oldPozisyon; }
    public void setOldPozisyon(String oldPozisyon) { this.oldPozisyon = oldPozisyon; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
