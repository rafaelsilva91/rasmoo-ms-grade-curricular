package com.rasmoo.cliente.escola.gradecurricular.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @GetMapping("/")
    public ResponseEntity<String> helloWordRest(){
        String value = "Olá mundo REST";
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

}
