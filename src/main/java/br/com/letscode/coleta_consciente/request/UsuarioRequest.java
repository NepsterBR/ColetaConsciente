package br.com.letscode.coleta_consciente.request;

import br.com.letscode.coleta_consciente.entity.Perfil;
import br.com.letscode.coleta_consciente.entity.Usuario;
import br.com.letscode.coleta_consciente.entity.enuns.TipoUser;
import br.com.letscode.coleta_consciente.repository.PerfilRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioRequest {

    private String email;
    private String password;
    private TipoUser perfil;

    public Usuario convert(PerfilRepository perfilRepository){
        String senhaCrypto = new BCryptPasswordEncoder().encode(this.password);
        return new Usuario(email, senhaCrypto, perfil);

    }

}