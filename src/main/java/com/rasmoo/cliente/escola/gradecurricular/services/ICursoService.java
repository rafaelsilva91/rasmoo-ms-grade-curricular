package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;

import java.util.List;
import java.util.Optional;

public interface ICursoService {

    public List<CursoEntity> findAll();

    public Optional<CursoEntity> findById(Long id) ;

    public CursoEntity insert(CursoDto cursoDto);

    public CursoEntity update(CursoDto cursoDto);

    public boolean delete(Long id);

//    public List<CursoDto> listarPorHoraMinima(long horaMinima);
//    public List<CursoDto> listarPorFrequencia(int frequency);


}
