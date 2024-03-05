package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;

import java.util.List;
import java.util.Optional;

public interface IMateriaService {

    public List<MateriaDto> findAll();

    public Optional<MateriaEntity> findById(Long id) ;

    public MateriaEntity insert(MateriaDto materiaDto);

    public MateriaEntity update(MateriaDto materiaDto);

    public boolean delete(Long id);

    public List<MateriaDto> listarPorHoraMinima(long horaMinima);
    public List<MateriaDto> listarPorFrequencia(int frequency);


}
