package br.com.letscode.coleta_consciente.entity;

import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.entity.enuns.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ponto_coleta")
public class PontoColeta {

    @Id
    private int cnpj;

    private String email;

    @Enumerated(EnumType.STRING)
    private Estados estado;

    private String cidade;

    @Column(name = "endereço")
    private String endereco;

    @Column(name = "nome_social")
    private String nomeSocial;

    private Float preco;

    @Enumerated (EnumType.STRING)
    @Column(name = "tipo_empresa")
    private TipoEmpresa tipoEmpresa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo")
    private TipoResiduo tipoResiduo;

    @Enumerated(EnumType.STRING)
    @Column(name =  "tipo_servico")
    private TipoServico tipoServico;

    private boolean status;
}
