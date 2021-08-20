package br.com.letscode.coleta_consciente.service;

import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.entity.enuns.TipoServico;
import br.com.letscode.coleta_consciente.repository.ClienteRepository;
import br.com.letscode.coleta_consciente.repository.PontoColetaRepository;
import br.com.letscode.coleta_consciente.response.PontoColetaResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    public List<PontoColetaResponse> assimilar(Estados estados, TipoEmpresa tipoEmpresa, TipoResiduo tipoResiduo,
                                               TipoServico tipoServico,
                                               float preco, int cpf, PontoColetaRepository pontoColetaRepository,
                                               ClienteRepository clienteRepository) {

        var lista = saldo(clienteRepository, pontoColetaRepository, cpf);
        return lista.stream().filter(l -> l.getPreco() <= preco)
                .filter(l -> l.getTipoEmpresa().equals(tipoEmpresa))
                .filter(l -> l.getTipoResiduo().equals(tipoResiduo))
                .filter(l -> l.getEstado().equals(estados))
                .filter(l -> l.getTipoServico().equals(tipoServico))
                .collect(Collectors.toList());
    }

    public List<PontoColetaResponse> saldo(ClienteRepository clienteRepository,
                                           PontoColetaRepository pontoColetaRepository, int cpf) {

        var cliente = clienteRepository.findById(cpf);
        var response = new PontoColetaResponse();
        var lista = response.convert(pontoColetaRepository.findAll());
        for (int i = 0; i < lista.toArray().length; i++) {
            lista.get(i).setTotalPagar(cliente.get().getQuantidade() * lista.get(i).getPreco());
        }
        return lista;
    }
}
