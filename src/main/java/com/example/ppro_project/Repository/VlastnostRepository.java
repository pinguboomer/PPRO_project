package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Vlastnost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VlastnostRepository extends JpaRepository<Vlastnost, Long> {
    Optional<Vlastnost> findById(Long idVlastnost);

    List<Vlastnost> findByKategorie(int kategorie);
}
