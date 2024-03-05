package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.enums.EnumHyperlink;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.services.ICursoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/curso")
public class CursoController {

    private ICursoService service;

    public CursoController(ICursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Response<List<CursoEntity>> >findAll() {
        Response<List<CursoEntity>> response = new Response<>();
        List<CursoEntity> list = this.service.findAll();

        response.setStatusCode(HttpStatus.OK.value());
        response.setData(list);
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CursoController.class).findAll())
                .withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CursoDto>> findById(@PathVariable Long id) {
        Response<CursoDto> response = new Response<>();

        Optional<CursoEntity> curso = this.service.findById(id);
        CursoEntity cursoEntity = curso.isPresent() ? curso.get() : null;
        CursoDto cursoDto = new CursoDto(cursoEntity);

        response.setData(cursoDto);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CursoController.class)
                        .findById(id))
                .withSelfRel());

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CursoController.class).insert(cursoDto))
                .withRel(EnumHyperlink.CADASTRAR.getValor()));

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CursoController.class).update(cursoDto))
                .withRel(EnumHyperlink.ATUALIZAR.getValor()));

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CursoController.class).delete(id))
                .withRel(EnumHyperlink.EXCLUIR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<CursoEntity>> insert(@Valid @RequestBody CursoDto cursoDto) {
        Response<CursoEntity> response = new Response<>();
        CursoEntity curso = this.service.insert(cursoDto);

        response.setData(curso);
        response.setStatusCode(HttpStatus.CREATED.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(CursoController.class).insert(cursoDto)).withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<CursoDto>> update(@Valid @RequestBody CursoDto cursoDto) {
        Response<CursoDto> response = new Response<>();

        CursoEntity cursoEntity = this.service.update(cursoDto);
        CursoDto curso = new CursoDto(cursoEntity);

        response.setData(curso);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CursoController.class)
                        .update(cursoDto)).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.service.delete(id));
    }

}
