package com.example.IMC.Service;


import com.example.IMC.Repositorio.RepositorioImc;
import com.example.IMC.models.DadosIMC;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ServiceImc {
    @Autowired
    RepositorioImc repositorioImc;

    public static double calcularImc( double peso,  double altura) {
        if ((peso <= 0) || (altura <= 0)) {
            throw new IllegalArgumentException("Peso e altura devem ser maiores que zero.");
        }

        double i = peso / (altura * altura);
        return i;
    }

    public static String classificarImc(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade grau 1";
        } else if (imc < 39.9) {
            return "Obesidade grau 2";
        } else {
            return "Obesidade grau 3 (mórbida)";

        }




    }
    }





