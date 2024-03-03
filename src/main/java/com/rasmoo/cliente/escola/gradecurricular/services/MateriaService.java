package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.controller.MateriaController;
import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateriaService {

    private IMateriaRepository repository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(IMateriaRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

//    @CachePut(unless = "#result.size()<3")
    @Override
    public List<MateriaDto> findAll() {
        try {
            List<MateriaDto> materiaDto = this.mapper.map(this.repository.findAll(), new TypeToken<List<MateriaDto>>() {}.getType());
            materiaDto.forEach(materia->{
                materia.add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(MateriaController.class)
                                .findById(materia.getId()))
                        .withSelfRel());
            });

            return materiaDto;
        } catch (Exception e) {
            throw new MateriaException("Falha ao retornar lista de Matérias", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @CachePut(key = "#id")
    @Override
    public Optional<MateriaEntity> findById(Long id) {
        try {
            Optional<MateriaEntity> materiaOptional = this.repository.findById(id);
            if (materiaOptional.isPresent()) {
                return Optional.ofNullable(this.mapper.map(materiaOptional.get(), MateriaEntity.class));
            }
            throw new MateriaException("Matéria não encontrada", HttpStatus.NOT_FOUND);
            //throw new Exception(); Forcando exception Internal_server_error
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException("Erro interno Identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MateriaEntity insert(MateriaDto materiaDto) {
        try {
            MateriaEntity materiaEntity = this.mapper.map(materiaDto, MateriaEntity.class);
            return this.repository.save(materiaEntity);
        } catch (Exception e) {
            throw new MateriaException("Falha ao inserir registro", HttpStatus.BAD_REQUEST);
        }
    }

//    @CacheEvict (key = "#materia.id")
    public MateriaEntity update(MateriaDto materiaDto) {
        this.findById(materiaDto.getId());
        MateriaEntity materia = this.mapper.map(materiaDto, MateriaEntity.class);

        return this.repository.save(materia);
    }

    @Override
    public boolean delete(Long id) {
        Optional<MateriaEntity> materia = this.findById(id);
        if (materia.isPresent()) {
            this.repository.delete(materia.get());
            return true;
        }

        return false;
    }

}


/*
*
* @Cacheable(value = "materia", key = "#id")
*Responsavel por manter a informação em cache, ou seja, na proxima consulta
*ele evite de consultar no banco de dados
*
* @CacheEvict
* Responsavel por limpar o caches
*
* */