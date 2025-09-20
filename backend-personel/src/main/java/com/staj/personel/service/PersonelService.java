package com.staj.personel.service;

import com.staj.personel.model.Personel;
import com.staj.personel.repository.PersonelRepository;
import com.staj.personel.messaging.RabbitMQPublisher;
import com.staj.personel.messaging.dto.NotificationMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PersonelService {

    private final PersonelRepository personelRepository;
    private final RabbitMQPublisher publisher;

    public PersonelService(PersonelRepository personelRepository, RabbitMQPublisher publisher) {
        this.personelRepository = personelRepository;
        this.publisher = publisher;
    }

    public List<Personel> getAllPersonel() {
        return personelRepository.findAll();
    }

    public Optional<Personel> getPersonelById(Long id) {
        return personelRepository.findById(id);
    }

    public Personel createPersonel(Personel personel) {
        Personel saved = personelRepository.save(personel);

        NotificationMessage msg = new NotificationMessage();
        msg.setEvent("CREATED");
        msg.setId(saved.getId());
        msg.setAd(saved.getAd());
        msg.setSoyad(saved.getSoyad());
        msg.setEmail(saved.getEmail());
        msg.setPozisyon(saved.getPozisyon());
        msg.setTimestamp(Instant.now());

        publisher.send(msg);

        return saved;
    }

    public Personel updatePersonel(Long id, Personel details) {
        return personelRepository.findById(id)
                .map(existing -> {

                    String oldAd = existing.getAd();
                    String oldSoyad = existing.getSoyad();
                    String oldEmail = existing.getEmail();
                    String oldPozisyon = existing.getPozisyon();

                    existing.setAd(details.getAd());
                    existing.setSoyad(details.getSoyad());
                    existing.setEmail(details.getEmail());
                    existing.setPozisyon(details.getPozisyon());

                    Personel saved = personelRepository.save(existing);

                    NotificationMessage msg = new NotificationMessage();
                    msg.setEvent("UPDATED");
                    msg.setId(saved.getId());
                    msg.setAd(saved.getAd());
                    msg.setSoyad(saved.getSoyad());
                    msg.setEmail(saved.getEmail());
                    msg.setPozisyon(saved.getPozisyon());

                    msg.setOldAd(oldAd);
                    msg.setOldSoyad(oldSoyad);
                    msg.setOldEmail(oldEmail);
                    msg.setOldPozisyon(oldPozisyon);

                    msg.setTimestamp(Instant.now());

                    publisher.send(msg);

                    return saved;
                })
                .orElseThrow(() -> new RuntimeException("Personel not found with id " + id));
    }

    public void deletePersonel(Long id) {
        Optional<Personel> opt = personelRepository.findById(id);
        personelRepository.deleteById(id);

        NotificationMessage msg = new NotificationMessage();
        msg.setEvent("DELETED");
        msg.setId(id);

        opt.ifPresent(p -> {
            msg.setAd(p.getAd());
            msg.setSoyad(p.getSoyad());
            msg.setEmail(p.getEmail());
            msg.setPozisyon(p.getPozisyon());
        });

        msg.setTimestamp(Instant.now());

        publisher.send(msg);
    }
}
