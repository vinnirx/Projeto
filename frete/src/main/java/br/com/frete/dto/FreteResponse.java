package br.com.frete.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreteResponse {
    private String transportadora;
    private double valorFrete;
    private int prazoEntrega;
    private String cep;
    private int httpStatus;
    private String mensagem;
    private int prazoExpedicao;
    private LocalDate dataEstimadaEntrega;
}