package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.SpelQueryContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private IMateriaRepository repository;

    public MateriaController(IMateriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> findAll(){
        List list = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> findById(@PathVariable Long id){
        Optional<MateriaEntity> materia = this.repository.findById(id);
        MateriaEntity value = materia.isPresent() ? materia.get() : null;
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

    @PostMapping
    public ResponseEntity<MateriaEntity> insert(@RequestBody MateriaEntity resquest) throws Exception {
        try {

            MateriaEntity materia = this.repository.save(resquest);
            return ResponseEntity.status(HttpStatus.CREATED).body(materia);

        }catch (Exception e){
            throw new Exception("Falha ao salvar registro", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaEntity> update(@RequestBody MateriaEntity request, @PathVariable Long id) throws Exception {

        return repository
                .findById(id)
                .map(c ->{
                     request.setId(c.getId());
                    repository.save(request);
                    return ResponseEntity.status(HttpStatus.OK).body(request);
                }).orElseThrow(() -> new Exception(String.valueOf(HttpStatus.NOT_FOUND)));

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<MateriaEntity> materia = this.repository.findById(id);
        if(materia.isPresent()){
            this.repository.delete(materia.get());
        }
    }


}
