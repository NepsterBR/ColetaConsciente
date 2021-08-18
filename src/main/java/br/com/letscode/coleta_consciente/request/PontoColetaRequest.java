package br.com.letscode.coleta_consciente.request;

import br.com.letscode.coleta_consciente.entity.PontoColeta;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.repository.ClienteRepository;
import br.com.letscode.coleta_consciente.repository.PontoColetaRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class PontoColetaRequest {

    public List<PontoColeta> assimilar(Estados estados, TipoEmpresa tipoEmpresa, TipoResiduo tipoResiduo,
                          float preco, int cpf, PontoColetaRepository pontoColetaRepository,
                                       ClienteRepository clienteRepository) {

        var lista = pontoColetaRepository.findAll();
        var cliente = clienteRepository.findById(cpf);
        PontoColeta pontoResquest = new PontoColeta();
        List<PontoColeta> requetsList = new ArrayList<>();

        for (int i = 0; i < lista.toArray().length; i++) {
            pontoResquest.setCnpj(lista.get(i).getCnpj());
            pontoResquest.setEmail(lista.get(i).getEmail());
            pontoResquest.setEstado(lista.get(i).getEstado());
            pontoResquest.setCidade(lista.get(i).getCidade());
            pontoResquest.setEndereco(lista.get(i).getEndereco());
            pontoResquest.setNomeSocial(lista.get(i).getNomeSocial());
            pontoResquest.setPreco(lista.get(i).getPreco());
            pontoResquest.setTipoEmpresa(lista.get(i).getTipoEmpresa());
            pontoResquest.setTipoResiduo(lista.get(i).getTipoResiduo());
            pontoResquest.setTotalPagar(lista.get(i).getPreco()*cliente.get().getQuantidade());
            requetsList.add(pontoResquest);
        }
        return requetsList.stream().filter(l -> l.getPreco() >= preco)
                .filter(l -> l.getTipoEmpresa().equals(tipoEmpresa))
                .filter(l -> l.getTipoResiduo().equals(tipoResiduo))
                .filter(l -> l.getEstado().equals(estados))
                .collect(Collectors.toList());
    }

}
