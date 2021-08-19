package br.com.letscode.coleta_consciente.repository;

import br.com.letscode.coleta_consciente.entity.PontoColeta;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Integer> {

    List<PontoColeta> findByEstado(Estados estado);
    List<PontoColeta> findByTipoResiduo(TipoResiduo tipoResiduo);
    List<PontoColeta> findByTipoEmpresa(TipoEmpresa tipoEmpresa);
}
