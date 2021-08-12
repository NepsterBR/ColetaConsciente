package br.com.letscode.coleta_consciente.entity;

import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "cliente")
public class Cliente {

    @Id
    private int cpf;

    private String nome;
    private String sobrenome;

    @Enumerated(EnumType.STRING)
    private Estados estado;

    private String cidade;

    @Column(name = "endereço")
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residuo")
    private TipoResiduo tipoResiduo;

    private float quantidade;
}
