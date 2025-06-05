package com.example.IMC.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table (name = "REGISTROS_IMC")
public class DadosIMC extends RepresentationModel<DadosIMC> implements Serializable {
    private static final long Serializable = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    private String usuario;
    private String sexo;
    private double peso;
    private double Imc;
    private double altura;
    private String classificacao;
    private LocalDateTime dataRegistro;


    @PrePersist
    protected void time() {
        dataRegistro = LocalDateTime.now();
    }



    public double getImc() {
        return Imc;
    }

    public void setImc(double imc) {
        Imc = imc;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}

