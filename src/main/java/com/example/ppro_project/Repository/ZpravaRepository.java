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

    @Query("SELECT zprava.idUtkani, zprava.vysledek, utkani.domaci, utkani.hoste," +
            " clen.prijmeni, clen.jmeno, clen.idFacr " +
            "FROM Zprava zprava " +
            "JOIN Utkani utkani ON zprava.idUtkani = utkani.idUtkani " +
            "JOIN Clen clen ON zprava.idR = clen.id " +
            "WHERE zprava.idDFA = :idDFA and zprava.stav = 1 order by zprava.id desc")
    List<Object[]> findPosudekByDFA(@Param("idDFA") int idDFA);

    @Query("SELECT zprava.idUtkani, zprava.vysledek, utkani.domaci, utkani.hoste," +
            " clen.prijmeni, clen.jmeno, clen.idFacr " +
            "FROM Zprava zprava " +
            "JOIN Utkani utkani ON zprava.idUtkani = utkani.idUtkani " +
            "JOIN Clen clen ON zprava.idR = clen.id " +
            "WHERE zprava.idDFA = :idDFA and zprava.stav = 0 order by zprava.id desc")
    List<Object[]> findPosudekByDFARozpracovane(@Param("idDFA") int idDFA);

    @Query("SELECT distinct zprava.idUtkani, zprava.vysledek, utkani.domaci, utkani.hoste, hodnoceni.roleR," +
            " clen.idFacr, clen.prijmeni, clen.jmeno,  hodnoceni.znamka, hodnoceni.znamka2 " +
            "FROM Zprava zprava " +
            "JOIN Utkani utkani ON zprava.idUtkani = utkani.idUtkani " +
            "JOIN Clen clen ON zprava.idR = clen.id or zprava.idAR1 = clen.id or zprava.idAR2 = clen.id " +
            "LEFT JOIN Hodnoceni hodnoceni ON zprava.id = hodnoceni.idZprava and ((hodnoceni.roleR = 'R'" +
            " and clen.id = zprava.idR) " +
            "            or (hodnoceni.roleR = 'AR1' and clen.id = zprava.idAR1) or" +
            "            (hodnoceni.roleR = 'AR2' and clen.id = zprava.idAR2)) " +
            "WHERE (zprava.idR = :idR or zprava.idAR1 = :idR or zprava.idAR2 = :idR)" +
            " and zprava.stav = 1")
    List<Object[]> findPosudekByR(@Param("idR") int idR);

    @Query("SELECT distinct zprava.idUtkani, zprava.vysledek, utkani.domaci, utkani.hoste, " +
            " clen.idFacr, clen.prijmeni, clen.jmeno, hodnoceni.znamka, hodnoceni.znamka2," +
            " clenDFA.idFacr, clenDFA.prijmeni, clenDFA.jmeno, " +
            " clenAR1.idFacr, clenAR1.prijmeni, clenAR1.jmeno, " +
            " clenAR2.idFacr, clenAR2.prijmeni, clenAR2.jmeno, zprava.id " +
            "FROM Zprava zprava " +
            "JOIN Utkani utkani ON zprava.idUtkani = utkani.idUtkani " +
            "JOIN Clen clen ON zprava.idR = clen.id " +
            "JOIN Clen clenDFA ON zprava.idDFA = clenDFA.id " +
            "LEFT JOIN Clen clenAR1 ON zprava.idAR1 = clenAR1.id " +
            "LEFT JOIN Clen clenAR2 ON zprava.idAR2 = clenAR2.id " +
            "LEFT JOIN Hodnoceni hodnoceni ON zprava.id = hodnoceni.idZprava and hodnoceni.roleR = 'R' " +
            " WHERE zprava.stav = 1 ORDER BY zprava.id desc")
    List<Object[]> findVsechnyPosudky();

    Zprava save(Zprava zprava);

    @Query("SELECT z FROM Zprava z WHERE z.idDFA = :idDFA")
    List<Zprava> findByIdDfa(@Param("idDFA") int idDFA);

    @Query("SELECT z FROM Zprava z WHERE z.idR = :idR or z.idAR1 = :idAR1 or z.idAR2 = :idAR2")
    List<Zprava> findByIdRorIdAr1orIdAr2(@Param("idR") int idR,
                                         @Param("idAR1") int idAR1,@Param("idAR2")  int idAR2);

    @Query("SELECT COUNT(z) FROM Zprava z WHERE (z.idR = :idR or z.idAR1 = :idR or z.idAR2 = :idR) and z.stav = 1")
    int countByIdR(@Param("idR") int idR);

    @Query("SELECT COUNT(z) FROM Zprava z WHERE z.idDFA = :idDFA and z.stav = 1")
    int countByIdDFA(@Param("idDFA") int idDFA);

}