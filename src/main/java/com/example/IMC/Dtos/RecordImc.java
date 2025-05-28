package com.example.IMC.Dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RecordImc(@NotBlank  String usuario, @NotBlank String sexo, @NotNull double peso, @NotNull double altura, @NotNull double imc, @DateTimeFormat LocalDateTime dataRegistro) {


}
