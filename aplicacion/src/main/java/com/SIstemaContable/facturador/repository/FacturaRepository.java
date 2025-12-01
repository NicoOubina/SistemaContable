package com.SIstemaContable.facturador.repository;

import com.SIstemaContable.facturador.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    boolean existsByNumero(String numero);
}
