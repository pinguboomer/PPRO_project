package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.HodnoceniPopis;
import com.example.ppro_project.Model.HodnoceniVlastnost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HodnoceniPopisRepository extends JpaRepository<HodnoceniPopis, Integer> {

    List<HodnoceniPopis> findByIdHodnoceni(int idHodnoceni);
    HodnoceniPopis findByIdHodnoceniAndTyp(int idHodnoceni, String typ);

    HodnoceniPopis save(HodnoceniPopis hodnoceniPopis);

}
