package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;

import java.util.List;
import java.util.Optional;

public interface IMateriaService {

    public List<Materia> findAll();

    public Optional<Materia> findById(Long id) ;

    public Materia insert(Materia materia);

    public Materia update (Materia materia, Long id);
    public void delete(Long id);

}
