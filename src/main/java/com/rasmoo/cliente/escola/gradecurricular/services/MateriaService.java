package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService{

    private IMateriaRepository repository;

    public MateriaService(IMateriaRepository repository) {
        this.repository = repository;
    }

    public List<Materia> findAll(){
        return this.repository.findAll();
    }

    public Optional<Materia> findById(Long id) {
        Optional<Materia> materia = this.repository.findById(id);
        if(!materia.isPresent()){
            return null;
        }
        return Optional.of(materia.get());
    }

    public Materia insert(Materia resquest) {
        Materia materia = this.repository.save(resquest);
        return materia;
    }

    public Materia update (Materia request, Long id){
        Optional<Materia> materia =  findById(id);
        Materia materiaAtualizada = materia.get();

        materiaAtualizada.setName(request.getName());
        materiaAtualizada.setHours(request.getHours());
        materiaAtualizada.setCode(request.getCode());
        materiaAtualizada.setFrequency(request.getFrequency());


        return this.repository.save(materiaAtualizada);

    }

    public void delete(Long id){
        Optional<Materia> materia = this.findById(id);
        if(materia.isPresent()){
            this.repository.delete(materia.get());
        }

    }
}
