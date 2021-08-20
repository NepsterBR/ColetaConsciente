package br.com.letscode.coleta_consciente.response;

import br.com.letscode.coleta_consciente.entity.PontoColeta;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.entity.enuns.TipoServico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class PontoColetaResponse {

    private int cnpj;
    private String email;
    private Estados estado;
    private String cidade;
    private String endereco;
    private String nomeSocial;
    private Float preco;
    private TipoEmpresa tipoEmpresa;
    private TipoResiduo tipoResiduo;
    private TipoServico tipoServico;
    private float totalPagar;

    public PontoColetaResponse(PontoColeta pontoColeta) {
        this.cnpj = pontoColeta.getCnpj();
        this.email = pontoColeta.getEmail();
        this.estado = pontoColeta.getEstado();
        this.cidade = pontoColeta.getCidade();
        this.endereco = pontoColeta.getEndereco();
        this.nomeSocial = pontoColeta.getNomeSocial();
        this.preco = pontoColeta.getPreco();
        this.tipoEmpresa = pontoColeta.getTipoEmpresa();
        this.tipoResiduo = pontoColeta.getTipoResiduo();
        this.tipoServico = pontoColeta.getTipoServico();
        this.totalPagar = 0f;
    }

    public List<PontoColetaResponse> convert (List<PontoColeta> pontoColetas){
        return pontoColetas.stream().map(PontoColetaResponse::new).collect(Collectors.toList());
    }
}
