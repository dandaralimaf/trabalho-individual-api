package org.serratec.trabalhoapi.repository;

import org.serratec.trabalhoapi.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

// Trabalha na classe Funcionario com o id do tipo Long
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
}