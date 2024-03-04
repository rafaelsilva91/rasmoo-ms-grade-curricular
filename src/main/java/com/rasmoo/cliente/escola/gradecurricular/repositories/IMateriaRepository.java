package com.rasmoo.cliente.escola.gradecurricular.repositories;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMateriaRepository extends JpaRepository<MateriaEntity, Long> {

    @Query("select m from MateriaEntity m where m.hours >= :horaMinima" )
    public List<MateriaEntity> findByHhoraMinima(@Param("horaMinima") long horaMinima);

}
