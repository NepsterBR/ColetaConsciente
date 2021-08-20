package br.com.letscode.coleta_consciente.request;

import br.com.letscode.coleta_consciente.entity.Cliente;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClienteRequest {

    private int cpf;
    private String nome;
    private String sobrenome;
    private Estados estado;
    private String cidade;
    private String endereco;
    private TipoResiduo tipoResiduo;
    private Float quantidade;

    public Cliente convert (){
        return new Cliente(this.cpf, this.nome,this.sobrenome, this.estado, this.cidade, this.endereco,
                 this.tipoResiduo, this.quantidade, true);
    }
}
