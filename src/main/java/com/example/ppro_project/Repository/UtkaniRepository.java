package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Utkani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtkaniRepository extends JpaRepository<Utkani, String> {
    Utkani findByIdUtkani(String idUtkani);

}