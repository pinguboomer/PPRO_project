package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Hodnoceni;
import com.example.ppro_project.Model.Soutez;
import com.example.ppro_project.Model.Utkani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HodnoceniRepository extends JpaRepository<Hodnoceni, Long> {
    Hodnoceni findByIdZprava(int idZprava);

}