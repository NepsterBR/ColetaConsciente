package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.Cliente;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.excecoes.NotFoundException;
import br.com.letscode.coleta_consciente.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cliente> create (@RequestBody Cliente cliente,
                                           UriComponentsBuilder uriComponentsBuilder){
        if (this.clienteRepository.findById(cliente.getCpf()).isEmpty()){
            this.clienteRepository.save(cliente);
            URI uri = uriComponentsBuilder.buildAndExpand(cliente).toUri();
            return ResponseEntity.created(uri).body(cliente);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
        if (this.clienteRepository.findById(cliente.getCpf()).isPresent()){
            this.clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscar_cpf")
    public ResponseEntity<Optional<Cliente>> findById(@RequestParam int cpf){
        try {
            return ResponseEntity.ok()
                    .body(this.clienteRepository.findById(cpf));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar_todos")
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok()
                .body(this.clienteRepository.findAll());
    }

    @GetMapping("/buscar_estado")
    public ResponseEntity<List<Cliente>> findByEstado(@RequestParam Estados estado) {
       try {
            return ResponseEntity.ok().body(clienteRepository.findByEstado(estado));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar_residuo")
    public ResponseEntity<List<Cliente>> findByResiduo(@RequestParam TipoResiduo tipoResiduo){
        try {
            return ResponseEntity.ok().body(clienteRepository.findByTipoResiduo(tipoResiduo));
        } catch (NotFoundException e) {
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
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
