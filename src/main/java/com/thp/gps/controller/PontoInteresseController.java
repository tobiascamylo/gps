package com.thp.gps.controller;

import com.thp.gps.controller.dto.ResponseMessageDTO;
import com.thp.gps.model.PontosInteresse;
import com.thp.gps.model.PontosInteresseDTO;
import com.thp.gps.repository.PontosInteresseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PontoInteresseController {

    private final PontosInteresseRepository repository;

    public PontoInteresseController(PontosInteresseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/pontos-de-interesse")
    public ResponseEntity<ResponseMessageDTO> pontosInteresseCriar(@RequestBody PontosInteresseDTO body) {
        repository.save(new PontosInteresse(body.nome(), body.x(), body.y()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseMessageDTO("Ponto de interesse criado com sucesso"));
    }

    @GetMapping("/listar-pontos-de-interesse")
    public ResponseEntity<List<PontosInteresse>> listarPontosInteresse() {
        List<PontosInteresse> listaInteresse = repository.findAll();
        return ResponseEntity.ok(listaInteresse);
    }

    @GetMapping("/listar-pontos-proximos")
    public ResponseEntity<List<PontosInteresse>> listarPontosProximos(
            @RequestParam("x") Long x,
            @RequestParam("y") Long y,
            @RequestParam("dmax") Long dmax
    ) {
        long xMin = x - dmax, xMax = x + dmax;
        long yMin = y - dmax, yMax = y + dmax;

        var pontosFiltrados = repository
                .findByXBetweenAndYBetween(xMin, xMax, yMin, yMax)
                .stream()
                .filter(p -> distanciaEuclidiana(x, y, p.getX(), p.getY()) <= dmax)
                .toList();

        return ResponseEntity.ok(pontosFiltrados);
    }

    @DeleteMapping("/pontos-de-interesse/{id}")
    public ResponseEntity<ResponseMessageDTO> deletarPontoInteresse(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessageDTO("Ponto de interesse com ID " + id + " não encontrado"));
        }
        repository.deleteById(id);
        return ResponseEntity
                .ok(new ResponseMessageDTO("Ponto de interesse com ID " + id + " deletado com sucesso"));
    }

    public double distanciaEuclidiana(long x1, long y1, long x2, long y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }
}
