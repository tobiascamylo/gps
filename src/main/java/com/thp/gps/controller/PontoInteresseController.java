package com.thp.gps.controller;

import com.thp.gps.model.PontosInteresse;
import com.thp.gps.model.PontosInteresseDTO;
import com.thp.gps.repository.PontosInteresseRepository;
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
    public ResponseEntity<Void> pontosInteresseCriar(@RequestBody PontosInteresseDTO body) {
        repository.save(new PontosInteresse(body.nome(), body.x(), body.y()));
        return ResponseEntity.ok().build();
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
    public ResponseEntity<Void> deletarPontoInteresse(@PathVariable("id") Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public double distanciaEuclidiana(long x1, long y1, long x2, long y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }
}
