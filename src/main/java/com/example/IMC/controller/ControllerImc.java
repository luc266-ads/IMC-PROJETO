package com.example.IMC.controller;

import com.example.IMC.Dtos.RecordImc;
import com.example.IMC.Repositorio.RepositorioImc;
import com.example.IMC.models.DadosIMC;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class ControllerImc {
@Autowired
RepositorioImc repositorioImc;

    //COMANDO POST (INSENRI OS DADOS )
@PostMapping("/dbimc")
public ResponseEntity<DadosIMC> saveIMc(@RequestBody @Valid RecordImc recordImc) {
    var imcModel = new DadosIMC();
    BeanUtils.copyProperties(recordImc, imcModel);
    return  ResponseEntity.status(HttpStatus.CREATED).body(repositorioImc.save(imcModel));
}

    //COMANDO GET(SELECIONAR TODOS DADOS)
    @GetMapping("/dbimc")
    public ResponseEntity<List<DadosIMC>> getAllImc(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioImc.findAll());

    }


    //COMANDO GET (SELECIONAR USUARIO PELO ID)
    @GetMapping("/dbimc/{id}")
    public ResponseEntity<Object> getIdImc(@PathVariable(value = "id") UUID id){
        Optional<DadosIMC> ImcO = repositorioImc.findById(id);
        if(ImcO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ImcO.get());

    }

    // COMANDO DELELE (DELETA PELA PROCURA DA ID )
    @DeleteMapping("/dbimc/{id}")
    public ResponseEntity<Object> deleteImc(@PathVariable(value = "id") UUID id){
        Optional<DadosIMC> imcD = repositorioImc.findById(id);
        if (imcD.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

        }
        repositorioImc.delete(imcD.get());
        return ResponseEntity.status(HttpStatus.OK).body("usuario deletado");

    }
    // COMANDO UPDATE (UTUALIZAR O USUARIO PELA ID)
    @PutMapping("/dbimc/{id}")
    public  ResponseEntity<Object> updateImc(@PathVariable (value =  "id") UUID id, @RequestBody @Valid RecordImc recordImc){
        Optional<DadosIMC> imcU = repositorioImc.findById(id);
        if (imcU.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

        }
        var imc = imcU.get();
        BeanUtils.copyProperties(recordImc, imc);
        repositorioImc.save(imc);
        return  ResponseEntity.status(HttpStatus.OK).body("Usuario atualizado");

    }
}
