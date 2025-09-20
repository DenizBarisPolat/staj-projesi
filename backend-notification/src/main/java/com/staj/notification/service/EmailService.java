package com.staj.notification.service;

import com.staj.notification.messaging.dto.NotificationMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from:notify@staj.local}")
    private String from;

    @Value("${notification.admin.cc:}")
    private String adminCc;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEventEmail(NotificationMessage msg) {
        String subject = switch (msg.getEvent()) {
            case "CREATED" -> "Personel Bilgisi Oluşturuldu";
            case "UPDATED" -> "Personel Bilgisi Güncellendi";
            case "DELETED" -> "Personel Silindi";
            default -> "Personel Bildirimi";
        };

        String body = buildBody(msg);

        sendSimple(subject, body, msg.getEmail(), null);

        if (adminCc != null && !adminCc.isBlank()) {
            sendSimple("(CC) " + subject, body, adminCc, null);
        }

        if ("UPDATED".equals(msg.getEvent())
                && msg.getOldEmail() != null
                && !msg.getOldEmail().isBlank()
                && !msg.getOldEmail().equalsIgnoreCase(msg.getEmail())) {
            sendSimple("E-posta Adresiniz Güncellendi", body, msg.getOldEmail(), null);
        }
    }

    private void sendSimple(String subject, String text, String to, @Nullable String cc) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);
        mail.setTo(to);
        if (cc != null && !cc.isBlank()) {
            mail.setCc(cc);
        }
        mail.setSubject(subject);
        mail.setText(text);
        mailSender.send(mail);
    }

    private String buildBody(NotificationMessage m) {
        StringBuilder sb = new StringBuilder();
        sb.append("Olay: ").append(m.getEvent()).append("\n")
          .append("Ad Soyad: ").append(m.getAd()).append(" ").append(m.getSoyad()).append("\n")
          .append("Email: ").append(m.getEmail()).append("\n")
          .append("Pozisyon: ").append(m.getPozisyon()).append("\n");

        if ("UPDATED".equals(m.getEvent())) {
            sb.append("\nDeğişiklikler:\n");
            diff(sb, "Ad", m.getOldAd(), m.getAd());
            diff(sb, "Soyad", m.getOldSoyad(), m.getSoyad());
            diff(sb, "Email", m.getOldEmail(), m.getEmail());
            diff(sb, "Pozisyon", m.getOldPozisyon(), m.getPozisyon());
        }
        return sb.toString();
    }

    private void diff(StringBuilder sb, String field, String oldV, String newV) {
        if (oldV == null) oldV = "";
        if (newV == null) newV = "";
        if (!oldV.equals(newV)) {
            sb.append("- ").append(field).append(": '")
              .append(oldV).append("' -> '").append(newV).append("'\n");
        }
    }
}
