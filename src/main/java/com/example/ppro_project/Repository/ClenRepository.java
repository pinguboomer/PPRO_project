package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Zprava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClenRepository extends JpaRepository<Clen, Integer> {
    Clen findByIdFacrAndHeslo(String idFacr, String heslo);

    Clen save(Clen clen);

    List<Clen> findByRole(String role);
    Clen findById(int id);

    Clen findByIdFacrAndRole(String idFacr, String role);
}