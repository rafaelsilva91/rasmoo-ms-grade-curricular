package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.CursoException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.ICursoRepository;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements ICursoService {

    private ICursoRepository repository;

    private IMateriaRepository materiaRepository;
    private ModelMapper mapper;

    @Autowired
    public CursoService(ICursoRepository repository, IMateriaRepository materiaRepository) {
        this.repository = repository;
        this.materiaRepository = materiaRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<CursoEntity> findAll() {
        try {

            return this.repository.findAll();
        } catch (Exception e) {
            throw new CursoException("Falha ao retornar lista de Cursos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<CursoEntity> findById(Long id) {
        try {
            Optional<CursoEntity> curso = this.repository.findById(id);
            if (curso.isPresent()) {
                return Optional.ofNullable(this.mapper.map(curso.get(), CursoEntity.class));
            }
            throw new CursoException("Curso não encontrado", HttpStatus.NOT_FOUND);
            //throw new Exception(); Forcando exception Internal_server_error
        } catch (CursoException c) {
            throw c;
        } catch (Exception e) {
            throw new CursoException("Erro interno Identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CursoEntity insert(CursoDto curso) {
        try {

            List<MateriaEntity> listMateria = new ArrayList<>();

            if(!curso.getMaterias().isEmpty()){
                curso.getMaterias().forEach(materia->{
                    if(this.materiaRepository.findById(materia).isPresent()){
                        listMateria.add(this.materiaRepository.findById(materia).get());
                    }
                });
            }

            CursoEntity cursoEntity = new CursoEntity();
            if (curso.getId() != null){
                cursoEntity.setId(curso.getId());
            }
            cursoEntity.setName(curso.getName());
            cursoEntity.setCode(curso.getCode());
            cursoEntity.setMaterias(listMateria);



            return this.repository.save(cursoEntity);

        } catch (Exception e) {
            throw new CursoException("Falha ao inserir registro", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public CursoEntity update(CursoDto cursoDto) {
        this.findById(cursoDto.getId());
        CursoEntity curso = this.mapper.map(cursoDto, CursoEntity.class);

        return this.repository.save(curso);
    }

    @Override
    public boolean delete(Long id) {
        Optional<CursoEntity> curso = this.findById(id);
        if (curso.isPresent()) {
            this.repository.delete(curso.get());
            return true;
        }

        return false;
    }
}
