package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Hodnoceni;
import com.example.ppro_project.Model.HodnoceniVlastnost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HodnoceniVlastnostRepository extends JpaRepository<HodnoceniVlastnost, Integer> {

    @Query("SELECT z FROM HodnoceniVlastnost z WHERE z.idPopis = :idPopis")
    List<HodnoceniVlastnost> findVlastnostiIdByIdPopis(@Param("idPopis") int idPopis);

    HodnoceniVlastnost save(HodnoceniVlastnost hodnoceniVlastnost);

    @Query("SELECT hv FROM HodnoceniVlastnost hv WHERE hv.idPopis = :idPopis" +
            " AND hv.idVlastnost = :idVlastnost")
    HodnoceniVlastnost findByPopisAndVlastnost(@Param("idPopis") int idPopis,
                                                   @Param("idVlastnost") int idVlastnost);

}