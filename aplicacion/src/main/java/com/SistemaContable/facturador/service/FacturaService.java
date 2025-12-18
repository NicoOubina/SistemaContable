package com.SistemaContable.facturador.service;

import com.SistemaContable.facturador.dto.FacturaDTO;
import com.SistemaContable.facturador.entity.Factura;
import com.SistemaContable.facturador.mapper.FacturaMapper;
import com.SistemaContable.facturador.repository.FacturaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;

    public FacturaService(FacturaRepository facturaRepository, FacturaMapper facturaMapper) {
        this.facturaRepository = facturaRepository;
        this.facturaMapper = facturaMapper;
    }

    public List<FacturaDTO> listarTodas() {
        return facturaRepository.findAll().stream()
                .map(facturaMapper::toDto)
                .collect(Collectors.toList());
    }

    public FacturaDTO guardar(FacturaDTO dto) {
        if (dto.getId() == null && facturaRepository.existsByNumero(dto.getNumero())) {
            throw new IllegalArgumentException("El número de factura ya existe");
        }
        Factura entidad = facturaMapper.toEntity(dto);
        Factura guardada = facturaRepository.save(entidad);
        return facturaMapper.toDto(guardada);
    }
}
