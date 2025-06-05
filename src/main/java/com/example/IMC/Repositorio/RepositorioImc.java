package com.example.IMC.Repositorio;


import com.example.IMC.models.DadosIMC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioImc extends JpaRepository<DadosIMC, UUID> {


}
