package br.com.letscode.coleta_consciente.request;

import br.com.letscode.coleta_consciente.entity.Usuario;
import br.com.letscode.coleta_consciente.entity.enuns.TipoUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class UsuarioRequest {

    private String email;
    private String password;
    private TipoUser perfil;

    public Usuario convert(){
        String senhaCrypto = new BCryptPasswordEncoder().encode(this.password);
        return new Usuario(email, senhaCrypto, perfil);

    }

}