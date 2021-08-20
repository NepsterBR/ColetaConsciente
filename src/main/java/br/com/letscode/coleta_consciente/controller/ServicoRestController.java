package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.entity.enuns.TipoServico;
import br.com.letscode.coleta_consciente.repository.ClienteRepository;
import br.com.letscode.coleta_consciente.repository.PontoColetaRepository;
import br.com.letscode.coleta_consciente.response.PontoColetaResponse;
import br.com.letscode.coleta_consciente.service.ConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/servico")
public class ServicoRestController {

    private final PontoColetaRepository pontoColetaRepository;
    private final ClienteRepository clienteRepository;


    @GetMapping
    public ResponseEntity<List<PontoColetaResponse>> consulta(@RequestParam Estados estados, TipoEmpresa tipoEmpresa,
                                                              TipoResiduo tipoResiduo,
                                                              TipoServico tipoServico,
                                                              float preco, int cpf){
        var consulta = new ConsultaService();
        var list = consulta.assimilar(estados, tipoEmpresa, tipoResiduo,
                tipoServico, preco, cpf, pontoColetaRepository, clienteRepository);

       return ResponseEntity.ok().body(list);
    }
}
