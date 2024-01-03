package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Soutez;
import com.example.ppro_project.Model.Utkani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SoutezRepository extends JpaRepository<Soutez, Long> {
    @Query("SELECT s FROM Soutez s WHERE LOWER(s.zkratka) LIKE LOWER(CONCAT('%', :zkratka, '%'))")
    Soutez findByZkratkas(@Param("zkratka") String zkratka);

    @Query("SELECT s FROM Soutez s WHERE LOWER(CONCAT('%', :zkratka, '%'))" +
            " LIKE LOWER(CONCAT('%', s.zkratka, '%'))")
    Soutez findByZkratka(@Param("zkratka") String zkratka);
}