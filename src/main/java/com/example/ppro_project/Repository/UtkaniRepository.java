package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Utkani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtkaniRepository extends JpaRepository<Utkani, String> {
    Utkani findByIdUtkani(String idUtkani);

    @Query("SELECT s FROM Utkani s WHERE s.idUtkani LIKE CONCAT('%', :rok, '%')")
    List<Utkani> findUtkaniByRok(@Param("rok") String rok);

}