package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Vlastnost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VlastnostRepository extends JpaRepository<Vlastnost, Long> {
    Optional<Vlastnost> findById(Long idVlastnost);

    List<Vlastnost> findByKategorie(int kategorie);


    @Query("SELECT v " +
            "    FROM Vlastnost v" +
            "    JOIN HodnoceniVlastnost hv ON v.id = hv.idVlastnost" +
            "    where hv.idClen = :idR and v.kategorie < 6 and hv.typ = :typ" +
            "    GROUP BY v.id" +
            "    ORDER BY COUNT(hv.id) DESC" +
            "    LIMIT 3")
    List<Vlastnost> findNejcastejsiVlastnostiRByIdR(@Param("idR") int idR, @Param("typ") int typ);

    @Query("SELECT v " +
            "    FROM Vlastnost v" +
            "    JOIN HodnoceniVlastnost hv ON v.id = hv.idVlastnost" +
            "    where hv.idClen = :idR and v.kategorie >= 6 and hv.typ = :typ " +
            "    GROUP BY v.id" +
            "    ORDER BY COUNT(hv.id) DESC" +
            "    LIMIT 3")
    List<Vlastnost> findNejcastejsiVlastnostiARByIdR(@Param("idR") int idR, @Param("typ") int typ);


}
