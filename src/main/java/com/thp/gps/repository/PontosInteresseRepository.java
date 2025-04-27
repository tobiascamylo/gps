package com.thp.gps.repository;

import com.thp.gps.model.PontosInteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PontosInteresseRepository extends JpaRepository<PontosInteresse, Long> {

    @Query(
            """
            SELECT p FROM tb_pontos_interesse p
            WHERE (p.x >= :xmin AND p.x <= :xmax)
            AND (p.y >= :ymin AND p.y <= :ymax)
            ORDER BY p.x, p.y
            """

    )
    List<PontosInteresse> findPontosInteresseProximos(@Param("xmin") Long xmin,
                                                      @Param("xmax") Long xmax,
                                                      @Param("ymin") Long ymin,
                                                      @Param("ymax") Long ymax);
}
