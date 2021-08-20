package br.com.letscode.coleta_consciente.request;

import br.com.letscode.coleta_consciente.entity.PontoColeta;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.entity.enuns.TipoServico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PontoColetaRequest {
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

    public PontoColeta convert (){
        return new PontoColeta(this.cnpj, this.email, this.estado, this.cidade, this.endereco, this.nomeSocial,
                this.preco, this.tipoEmpresa, this.tipoResiduo, this.tipoServico, true);
    }

}
