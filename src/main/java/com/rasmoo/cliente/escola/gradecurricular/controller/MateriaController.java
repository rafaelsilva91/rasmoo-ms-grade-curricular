package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import jakarta.validation.Valid;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/materia")
public class MateriaController {

    private static final String DELETE = "DELETE";
    private static final String UPDATE = "UPDATE";
    private static final String INSERT = "INSERT";

    private IMateriaService service;

    public MateriaController(IMateriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>> >findAll() {
        Response<List<MateriaDto>> response = new Response<>();
        List<MateriaDto> list = this.service.findAll();

        response.setStatusCode(HttpStatus.OK.value());
        response.setData(list);
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class).findAll())
                .withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/minimumHour/{miniMumHour}")
    public ResponseEntity<Response<List<MateriaDto>>> findByMinimumHour(@PathVariable Long miniMumHour) {
        Response<List<MateriaDto>> response = new Response<>();

        List<MateriaDto> materiaDto = this.service.listarPorHoraMinima(miniMumHour);

        response.setData(materiaDto);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class)
                        .findByMinimumHour(miniMumHour))
                .withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/frequencia/{frequency}")
    public ResponseEntity<Response<List<MateriaDto>>> findByFrequencia(@PathVariable int frequency) {
        Response<List<MateriaDto>> response = new Response<>();

        List<MateriaDto> materiaDto = this.service.listarPorFrequencia(frequency);

        response.setData(materiaDto);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class)
                        .findByFrequencia(frequency))
                .withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MateriaDto>> findById(@PathVariable Long id) {
        Response<MateriaDto> response = new Response<>();

        Optional<MateriaEntity> materia = this.service.findById(id);
        MateriaEntity materiaEntity = materia.isPresent() ? materia.get() : null;
        MateriaDto materiaDto = new MateriaDto(materiaEntity);

        response.setData(materiaDto);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class)
                        .findById(id))
                .withSelfRel());

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class).insert(materiaDto))
                .withRel(INSERT));

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class).update(materiaDto))
                .withRel(UPDATE));

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class).delete(id))
                .withRel(DELETE));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<Response<MateriaDto>> insert(@Valid @RequestBody MateriaDto materiaDto) {
        Response<MateriaDto> response = new Response<>();
        MateriaEntity materiaEntity = this.service.insert(materiaDto);
        MateriaDto materia = new MateriaDto(materiaEntity);

        response.setData(materia);
        response.setStatusCode(HttpStatus.CREATED.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(MateriaController.class).insert(materiaDto)).withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<MateriaDto>> update(@Valid @RequestBody MateriaDto materiaDto) {
        Response<MateriaDto> response = new Response<>();

        MateriaEntity materiaEntity = this.service.update(materiaDto);
        MateriaDto materia = new MateriaDto(materiaEntity);

        response.setData(materia);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(MateriaController.class)
                        .update(materiaDto)).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.service.delete(id));
    }

}
