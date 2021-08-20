package br.com.letscode.coleta_consciente.request;

import br.com.letscode.coleta_consciente.entity.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilRequest {

    private  String nome;

    public Perfil convert(){
        return new Perfil(nome);
    }
}
