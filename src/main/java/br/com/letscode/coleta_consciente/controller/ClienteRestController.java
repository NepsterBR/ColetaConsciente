package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.Cliente;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.repository.ClienteRepository;
import br.com.letscode.coleta_consciente.request.ClienteRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

    private final ClienteRepository clienteRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteRequest> create (@RequestBody ClienteRequest clienteRequest,
                                           UriComponentsBuilder uriComponentsBuilder){
        var cliente = clienteRequest.convert();
        if (this.clienteRepository.findById(cliente.getCpf()).isEmpty()){
            this.clienteRepository.save(cliente);
            URI uri = uriComponentsBuilder.buildAndExpand(clienteRequest).toUri();
            return ResponseEntity.created(uri).body(clienteRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ClienteRequest> atualizar(@RequestBody ClienteRequest clienteRequest) {
        var cliente = clienteRequest.convert();
        if (this.clienteRepository.findById(cliente.getCpf()).isPresent()){
            this.clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(clienteRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscar-cpf")
    public ResponseEntity<Optional<Cliente>> findById(@RequestParam int cpf){
        try {
            return ResponseEntity.ok()
                    .body(this.clienteRepository.findById(cpf));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok()
                .body(this.clienteRepository.findAll());
    }

    @GetMapping("/buscar-estado")
    public ResponseEntity<List<Cliente>> findByEstado(@RequestParam Estados estado) {
       try {
            return ResponseEntity.ok().body(clienteRepository.findByEstado(estado));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar-residuo")
    public ResponseEntity<List<Cliente>> findByResiduo(@RequestParam TipoResiduo tipoResiduo){
        try {
            return ResponseEntity.ok().body(clienteRepository.findByTipoResiduo(tipoResiduo));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/deletar")
    public ResponseEntity<?> deleteById(@RequestParam int cpf) {
        var usuario = this.clienteRepository.findById(cpf);
        try {
            if (usuario.isPresent()) {
                usuario.get().setStatus(false);
                this.clienteRepository.save(usuario.get());
                return ResponseEntity.ok().build();
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
