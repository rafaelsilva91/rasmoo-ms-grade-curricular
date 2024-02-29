package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.services.MateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    private MateriaService service;

    public MateriaController(MateriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Materia>> findAll(){
        List list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> findById(@PathVariable Long id){
        Optional<Materia> materia = this.service.findById(id);
        Materia value = materia.isPresent() ? materia.get() : null;
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

    @PostMapping
    public ResponseEntity<Materia> insert(@RequestBody Materia resquest) {
        Materia materia = this.service.insert(resquest);
        return ResponseEntity.status(HttpStatus.CREATED).body(materia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> update(@RequestBody Materia request, @PathVariable Long id) throws Exception {
        Optional<Materia> materia = Optional.ofNullable(this.service.update(request, id));

        return ResponseEntity.status(HttpStatus.OK).body(materia.get());

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }


}
