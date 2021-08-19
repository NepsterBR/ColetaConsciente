package br.com.letscode.coleta_consciente.repository;

import br.com.letscode.coleta_consciente.entity.Cliente;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByEstado(Estados estado);
    List<Cliente> findByTipoResiduo(TipoResiduo tipoResiduo);
}
