package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Utkani;
import com.example.ppro_project.Model.Zprava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZpravaRepository extends JpaRepository<Zprava, Long> {
    Zprava findByIdUtkani(int idUtkani);
}