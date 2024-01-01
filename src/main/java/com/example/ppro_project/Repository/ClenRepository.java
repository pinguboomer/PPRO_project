package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Clen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClenRepository extends JpaRepository<Clen, Long> {
    Clen findByIdFacrAndHeslo(String idFacr, String heslo);

    List<Clen> findByRole(String role);
}