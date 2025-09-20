package com.staj.personel.repository;

import com.staj.personel.model.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Long> {
}
