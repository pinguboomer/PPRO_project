package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Delegace;
import com.example.ppro_project.Model.HodnoceniPopis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DelegaceRepository extends JpaRepository<Delegace, Integer> {

    Delegace findById(int id);
  //  Delegace findByIdUtkani(String id_utkani);

    Delegace save(Delegace delegace);

    @Query("SELECT d " +
            "    FROM Delegace d" +
            "    where d.r.id = :idClen or d.ar1.id = :idClen" +
            " or d.ar2.id  = :idClen or d.dfa.id  = :idClen or d.td.id  = :idClen")
    List<Delegace> findDelegaceByIdClen(@Param("idClen") int idClen);
}