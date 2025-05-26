package com.example.IMC.Dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RecordImc(@NotBlank  String usuario, @NotBlank String sexo, @NotNull BigDecimal peso, @NotNull BigDecimal altura) {


}
