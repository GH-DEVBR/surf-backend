package com.example.surf_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherDTO {
    private String praia;
    private String descricao;
    private String temperatura;
    private String vento;
    private String umidade;
}

