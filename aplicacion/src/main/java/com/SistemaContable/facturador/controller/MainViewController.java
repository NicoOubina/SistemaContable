package com.SistemaContable.facturador.controller;

import com.SistemaContable.facturador.dto.FacturaDTO;
import com.SistemaContable.facturador.service.FacturaService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

@Controller
public class MainViewController {

    private final FacturaService facturaService;
    private final ObservableList<FacturaDTO> facturas = FXCollections.observableArrayList();

    @FXML
    private TableView<FacturaDTO> tablaFacturas;

    @FXML
    private TableColumn<FacturaDTO, String> numeroColumna;

    @FXML
    private TableColumn<FacturaDTO, String> clienteColumna;

    @FXML
    private TableColumn<FacturaDTO, LocalDate> fechaColumna;

    @FXML
    private TableColumn<FacturaDTO, BigDecimal> totalColumna;

    @FXML
    private TextField numeroField;

    @FXML
    private TextField clienteField;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private TextField totalField;

    @FXML
    private Label mensajeLabel;

    @FXML
    private Button guardarButton;

    public MainViewController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @FXML
    public void initialize() {
        numeroColumna.setCellValueFactory(new PropertyValueFactory<>("numero"));
        clienteColumna.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        totalColumna.setCellValueFactory(new PropertyValueFactory<>("total"));

        tablaFacturas.setItems(facturas);
        fechaPicker.setValue(LocalDate.now());
        recargarFacturas();
    }

    @FXML
    public void guardarFactura(ActionEvent event) {
        try {
            FacturaDTO nueva = new FacturaDTO();
            nueva.setNumero(numeroField.getText());
            nueva.setCliente(clienteField.getText());
            nueva.setFecha(fechaPicker.getValue());
            nueva.setTotal(parseMonto(totalField.getText()));

            FacturaDTO guardada = facturaService.guardar(nueva);
            if (guardada != null && !facturas.contains(guardada)) {
                facturas.add(guardada);
            }
            limpiarFormulario();
            mostrarMensaje("Factura guardada", false);
        } catch (Exception e) {
            mostrarMensaje(e.getMessage(), true);
        }
    }

    private BigDecimal parseMonto(String valor) {
        Objects.requireNonNull(valor, "El monto es obligatorio");
        try {
            return new BigDecimal(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Monto inválido");
        }
    }

    private void recargarFacturas() {
        facturas.setAll(facturaService.listarTodas());
    }

    private void limpiarFormulario() {
        numeroField.clear();
        clienteField.clear();
        fechaPicker.setValue(LocalDate.now());
        totalField.clear();
    }

    private void mostrarMensaje(String mensaje, boolean error) {
        mensajeLabel.setText(mensaje);
        mensajeLabel.getStyleClass().removeAll("mensaje-error", "mensaje-ok");
        mensajeLabel.getStyleClass().add(error ? "mensaje-error" : "mensaje-ok");
    }
}
