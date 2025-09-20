# Personel Yönetim Sistemi

Bu proje, Spring Boot, RabbitMQ ve React teknolojileri kullanılarak geliştirilmiş bir Personel Yönetim Sistemidir.  
Amaç; personel ekleme, güncelleme, silme ve listeleme işlemlerini gerçekleştirmek ve bu işlemlerle ilgili bildirimleri RabbitMQ üzerinden yönetmektir.

---

## Kullanılan Teknolojiler
- Backend - Personel: Spring Boot, Spring Data JPA, H2 Database  
- Backend - Notification: Spring Boot, RabbitMQ Listener  
- Frontend: React.js (Axios ile backend iletişimi)  
- Mesajlaşma Altyapısı: RabbitMQ  

---

## Özellikler
- Personel ekleme, listeleme, güncelleme ve silme  
- RabbitMQ aracılığıyla bildirim gönderimi  
- H2 veritabanında personel verilerinin saklanması  
- Frontend üzerinden CRUD işlemlerinin yapılabilmesi  

---

## Kurulum Adımları

### Backend - Personel
1. `cd backend-personel`  
2. `mvnw spring-boot:run`  

### Backend - Notification
1. `cd backend-notification`  
2. `mvnw spring-boot:run`  

### Frontend
1. `cd frontend`  
2. `npm install`  
3. `npm start`  

---

## Veritabanı
Proje H2 (file-based) veritabanı kullanmaktadır.  
Veritabanına erişim için tarayıcıdan aşağıdaki adrese gidilebilir:  

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## RabbitMQ
RabbitMQ yönetim paneline aşağıdaki adresten erişilebilir:  

[http://localhost:15672](http://localhost:15672)

- Kullanıcı Adı: `guest`  
- Şifre: `guest`  

---

## Geliştirici
Deniz Barış Polat
