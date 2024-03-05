package com.rasmoo.cliente.escola.gradecurricular.repositories;

import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<CursoEntity, Long> {

}
