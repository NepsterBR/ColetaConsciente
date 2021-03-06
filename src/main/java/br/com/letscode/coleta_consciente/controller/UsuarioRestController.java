package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.Usuario;
import br.com.letscode.coleta_consciente.repository.PerfilRepository;
import br.com.letscode.coleta_consciente.repository.UsuarioRepository;
import br.com.letscode.coleta_consciente.request.UsuarioRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(
            @RequestBody UsuarioRequest usuarioRequest,
            UriComponentsBuilder uriComponentsBuilder){

        Usuario usuario = usuarioRequest.convert();
        usuarioRepository.save(usuario);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

}
