package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombreAutor);

    List<Autor> findByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(int anio, int anio1);
}
