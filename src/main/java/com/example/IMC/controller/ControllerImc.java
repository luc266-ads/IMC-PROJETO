package com.example.IMC.controller;

import com.example.IMC.Dtos.RecordImc;
import com.example.IMC.Repositorio.RepositorioImc;
import com.example.IMC.Service.ServiceImc;
import com.example.IMC.models.DadosIMC;
import jakarta.validation.Valid;
import org.hibernate.sql.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static  org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ControllerImc {
@Autowired
RepositorioImc repositorioImc;


    //COMANDO POST (INSENRI OS DADOS )
@PostMapping("/dbimc")
    public ResponseEntity<Map<String, Object>> calcularImc(@RequestBody @Valid RecordImc recordImc) {
        // Calcula o IMC
        double imc = ServiceImc.calcularImc(recordImc.peso(), recordImc.altura());
        String classificacao = ServiceImc.classificarImc(imc);

        // Cria o registro para salvar no banco
        DadosIMC imcModel = new DadosIMC();
        imcModel.setUsuario(recordImc.usuario());
        imcModel.setSexo(recordImc.sexo());
        imcModel.setPeso(recordImc.peso());
        imcModel.setAltura(recordImc.altura());
        imcModel.setImc(imc);
        imcModel.setClassificacao(classificacao);


        // Salva no repositório
        repositorioImc.save(imcModel);

        // Monta a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("imc", imc);
        response.put("classificacao", classificacao);

        // Retorna 201 CREATED com o corpo da resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    //COMANDO GET(SELECIONAR TODOS DADOS)
    @GetMapping("/dbimc")
    public ResponseEntity<List<DadosIMC>> getAllImc(){
    List<DadosIMC> imcList = repositorioImc.findAll();
    if(!imcList.isEmpty()){
        for (DadosIMC IMC: imcList){
            UUID id = IMC.getId();
            IMC.add(linkTo(methodOn(ControllerImc.class).getIdImc(id)).withSelfRel());

        }
    }
        return ResponseEntity.status(HttpStatus.OK).body(imcList);

    }


    //COMANDO GET (SELECIONAR USUARIO PELO ID)
    @GetMapping("/dbimc/{id}")
    public ResponseEntity<Object> getIdImc(@PathVariable(value = "id") UUID id){
        Optional<DadosIMC> ImcO = repositorioImc.findById(id);
        if(ImcO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        ImcO.get().add(linkTo(methodOn(ControllerImc.class).getAllImc()).withRel("Lista de Usuarios"));
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

    //DELETE TODOS OS DADOS
    @DeleteMapping("/dbimc")
    public ResponseEntity<String> imcDeleteAll(){
       repositorioImc.deleteAll();
       return ResponseEntity.status(HttpStatus.OK).body("Dados delatado");

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
