package org.serratec.trabalhoapi.controller;

import java.util.List;

import org.serratec.trabalhoapi.domain.Endereco;
import org.serratec.trabalhoapi.domain.Funcionario;
import org.serratec.trabalhoapi.repository.FuncionarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	// Injeta o FuncionarioRepository para acessarmos o banco de dados
    private final FuncionarioRepository repository;
    
    public FuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    // Retorna lista dos usuários cadastrados
    @GetMapping
    public List<Funcionario> buscarTodos() {
        return repository.findAll();
    }

    // Retorna usuário com o ID requisitado
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Cria usuário e salva
    @PostMapping
    public ResponseEntity<Funcionario> criar(@Valid @RequestBody Funcionario funcionario) {
        Funcionario salvo = repository.save(funcionario);
        return ResponseEntity.ok(salvo);
    }

    // Atualiza os dados do ID requisitado
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
        return repository.findById(id).map(existente -> {
            existente.setNome(funcionario.getNome());

            Endereco enderecoExistente = existente.getEndereco();
            Endereco novoEndereco = funcionario.getEndereco();

            enderecoExistente.setRua(novoEndereco.getRua());
            enderecoExistente.setCidade(novoEndereco.getCidade());
            enderecoExistente.setEstado(novoEndereco.getEstado());
            enderecoExistente.setCep(novoEndereco.getCep());

            repository.save(existente);
            return ResponseEntity.ok(existente);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Deleta usuário de acordo com o ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id).map(funcionario -> {
            repository.delete(funcionario);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

