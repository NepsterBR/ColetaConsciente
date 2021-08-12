package br.com.letscode.coleta_consciente.repository;

import br.com.letscode.coleta_consciente.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
