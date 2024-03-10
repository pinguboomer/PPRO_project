package com.example.ppro_project.Repository;

import com.example.ppro_project.Model.Clen;
import com.example.ppro_project.Model.Zprava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClenRepository extends JpaRepository<Clen, Integer> {
    Clen findByIdFacrAndHeslo(String idFacr, String heslo);

    Clen save(Clen clen);

    List<Clen> findByRole(String role);
    Clen findById(int id);

  //  @Modifying
  //  @Query("UPDATE Clen c SET c.heslo = :noveHeslo WHERE c.id = :id")
  //  void updateHeslo(@Param("id") int id, @Param("noveHeslo") String noveHeslo);

    Clen findByIdFacrAndRole(String idFacr, String role);
    List<Clen> findByEmailAndRole(String email, String role);

    @Query("SELECT z.idUtkani, h.znamka, h.znamka2, " +
            " c.prijmeni, z.idR, z.idAR1,z.idAR2,c.idFacr, h.roleR " +
            " FROM Clen c " +
            " JOIN Zprava z ON c.id = z.idAR1 OR c.id = z.idAR2 " +
            "  JOIN Hodnoceni h ON z.id = h.idZprava " +
            " WHERE c.id = :idR AND (c.id = z.idAR1 AND h.roleR = 'AR1' OR c.id = z.idAR2 AND h.roleR = 'AR2')" +
            " AND z.stav = 1 order by h.znamka desc, h.znamka2 desc limit 1")
    List<Object[]> findNejlepsiZnamkaARByIdR(@Param("idR") int idR);

    @Query("SELECT z.idUtkani, h.znamka, h.znamka2, " +
            " c.prijmeni, z.idR, z.idAR1,z.idAR2,c.idFacr, h.roleR  " +
            " FROM Clen c " +
            " JOIN Zprava z ON c.id = z.idR" +
            "  JOIN Hodnoceni h ON z.id = h.idZprava " +
            " WHERE c.id = :idR AND c.id = z.idR AND h.roleR = 'R' and z.stav = 1 " +
            " order by h.znamka desc, h.znamka2 desc limit 1")
    List<Object[]> findNejlepsiZnamkaRByIdR(@Param("idR") int idR);

    @Query("SELECT z.idUtkani, h.znamka, h.znamka2," +
            " c.prijmeni, z.idR, z.idAR1,z.idAR2,c.idFacr, h.roleR  " +
            " FROM Clen c " +
            " JOIN Zprava z ON c.id = z.idAR1 OR c.id = z.idAR2 " +
            "  JOIN Hodnoceni h ON z.id = h.idZprava " +
            " WHERE c.id = :idR AND (c.id = z.idAR1 AND h.roleR = 'AR1' OR c.id = z.idAR2 AND h.roleR = 'AR2')" +
            " AND z.stav = 1 order by h.znamka asc, h.znamka2 asc limit 1")
    List<Object[]> findNejhorsiZnamkaARByIdR(@Param("idR") int idR);

    @Query("SELECT z.idUtkani, h.znamka, h.znamka2," +
            " c.prijmeni, z.idR, z.idAR1,z.idAR2,c.idFacr, h.roleR " +
            " FROM Clen c " +
            " JOIN Zprava z ON c.id = z.idR" +
            "  JOIN Hodnoceni h ON z.id = h.idZprava " +
            " WHERE c.id = :idR AND c.id = z.idR AND h.roleR = 'R' and z.stav = 1 " +
            " order by h.znamka asc, h.znamka2 asc limit 1")
    List<Object[]> findNejhorsiZnamkaRByIdR(@Param("idR") int idR);
}