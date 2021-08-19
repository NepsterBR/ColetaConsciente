package br.com.letscode.coleta_consciente.request;

import br.com.letscode.coleta_consciente.entity.Perfil;
import br.com.letscode.coleta_consciente.entity.Usuario;
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
    private List<Integer> perfil;

    public Usuario convert(PerfilRepository perfilRepository){

        String senhaCrypto = new BCryptPasswordEncoder().encode(this.password);
        List<Perfil> perfis = new ArrayList<>();
        this.perfil.stream().map(id -> perfis.add(perfilRepository.findById(id).get()));

        return new Usuario(email, senhaCrypto, perfis);

    }

}