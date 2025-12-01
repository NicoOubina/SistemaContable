package com.SistemaContable.facturador.repository;

import com.SistemaContable.facturador.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    boolean existsByNumero(String numero);
}
