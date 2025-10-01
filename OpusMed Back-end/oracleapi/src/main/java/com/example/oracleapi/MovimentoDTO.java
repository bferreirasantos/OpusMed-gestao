package com.example.oracleapi;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovimentoDTO {
    public int id;
    public String nomeColaborador;
    public String nomeProduto;
    public String codigoBarras;
    public int idColaborador;
    public int idProduto;
    public LocalDate data;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime horarioEntrada;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime horarioSaida;
    public Integer quantidadeEntrada;
    public Integer quantidadeSaida;
}
