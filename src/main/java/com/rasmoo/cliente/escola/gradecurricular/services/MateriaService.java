package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService {

    private IMateriaRepository repository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(IMateriaRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    public List<MateriaDto> findAll() {
        try {
            return this.mapper.map(this.repository.findAll(), new TypeToken<List<MateriaDto>>() {}.getType());
        } catch (Exception e) {
            throw new MateriaException("Falha ao retornar lista de Matérias", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    public MateriaEntity insert(MateriaDto materiaDto) {
        try {
            MateriaEntity materiaEntity = this.mapper.map(materiaDto, MateriaEntity.class);
            return this.repository.save(materiaEntity);
        } catch (Exception e) {
            throw new MateriaException("Falha ao inserir registro", HttpStatus.BAD_REQUEST);
        }
    }

    public MateriaEntity update(MateriaDto materiaDto) {
        this.findById(materiaDto.getId());
        MateriaEntity materia = this.mapper.map(materiaDto, MateriaEntity.class);

        return this.repository.save(materia);
    }

    public void delete(Long id) {
        Optional<MateriaEntity> materia = this.findById(id);
        if (materia.isPresent()) {
            this.repository.delete(materia.get());
        }

    }
}
