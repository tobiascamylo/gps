package com.thp.gps.controller;

import com.thp.gps.model.PontosInteresse;
import com.thp.gps.model.PontosInteresseDTO;
import com.thp.gps.repository.PontosInteresseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PontoInteresseController {

    private final PontosInteresseRepository repository;

    public PontoInteresseController(PontosInteresseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/pontos-de-interesse")
    public ResponseEntity<Void>pontosInteresseCriar(@RequestBody PontosInteresseDTO body){
        repository.save(new PontosInteresse(body.nome(), body.x(), body.y()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar-pontos-de-interesse")
    public ResponseEntity<List<PontosInteresse>> listarPontosInteresse(){
        List<PontosInteresse> listaInteresse = repository.findAll();
        return ResponseEntity.ok(listaInteresse);
    }
}
