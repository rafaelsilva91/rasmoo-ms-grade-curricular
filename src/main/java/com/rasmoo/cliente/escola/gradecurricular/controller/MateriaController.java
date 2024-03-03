package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/materia")
public class MateriaController {

    private IMateriaService service;

    public MateriaController(IMateriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MateriaDto>> findAll(){
        List<MateriaDto> list = this.service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> findById(@PathVariable Long id){
        Optional<MateriaEntity> materia = this.service.findById(id);
        MateriaEntity materiaEntity = materia.isPresent() ? materia.get() : null;
        MateriaDto materiaDto = new MateriaDto(materiaEntity);
        return ResponseEntity.status(HttpStatus.OK).body(materiaDto);
    }
    @PostMapping
    public ResponseEntity<MateriaDto> insert(@Valid @RequestBody MateriaDto materiaDto) {
        MateriaEntity materiaEntity = this.service.insert(materiaDto);
        MateriaDto materia = new MateriaDto(materiaEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(materia);
    }

    @PutMapping
    public ResponseEntity<MateriaDto> update(@Valid @RequestBody MateriaDto materiaDto) {
        MateriaEntity materiaEntity = this.service.update(materiaDto);
        MateriaDto materia = new MateriaDto(materiaEntity);

        return ResponseEntity.status(HttpStatus.OK).body(materia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

}
