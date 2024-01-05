package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Utkani;
import com.example.ppro_project.Model.Zprava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZpravaRepository extends JpaRepository<Zprava, Integer> {
    Zprava findByIdUtkani(String idUtkani);
    Zprava save(Zprava zprava);

    @Query("SELECT z FROM Zprava z WHERE z.idDFA = :idDFA")
    List<Zprava> findByIdDfa(@Param("idDFA") int idDFA);

    @Query("SELECT z FROM Zprava z WHERE z.idR = :idR or z.idAR1 = :idAR1 or z.idAR2 = :idAR2")
    List<Zprava> findByIdRorIdAr1orIdAr2(@Param("idR") int idR,
                                         @Param("idAR1") int idAR1,@Param("idAR2")  int idAR2);
}