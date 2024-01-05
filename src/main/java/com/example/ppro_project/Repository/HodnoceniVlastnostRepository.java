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

    @Query("SELECT z.idVlastnost FROM HodnoceniVlastnost z WHERE z.idHodnoceni = :idHodnoceni")
    List<Integer> findVlastnostiIdByIdHodnoceni(@Param("idHodnoceni") int idHodnoceni);

    HodnoceniVlastnost save(HodnoceniVlastnost hodnoceniVlastnost);

    @Query("SELECT hv FROM HodnoceniVlastnost hv WHERE hv.idHodnoceni = :idHodnoceni" +
            " AND hv.idVlastnost = :idVlastnost")
    HodnoceniVlastnost findByHodnoceniAndVlastnost(@Param("idHodnoceni") int idHodnoceni,
                                                   @Param("idVlastnost") int idVlastnost);

}