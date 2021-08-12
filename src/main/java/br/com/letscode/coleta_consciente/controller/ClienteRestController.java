package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.Cliente;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
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
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.clienteRepository.findById(cpf));
    }

    @GetMapping("/buscar_todos")
    public List<Cliente> findAll(){
        return this.clienteRepository.findAll();
    }

    @GetMapping("/buscar_estado")
    public List<Cliente> findByEstado(@RequestParam Estados estado) {
        return this.clienteRepository.findByEstado(estado);
    }

    @GetMapping("/buscar_residuo")
    public List<Cliente> findByResiduo(@RequestParam TipoResiduo tipoResiduo){
        return this.clienteRepository.findByTipoResiduo(tipoResiduo);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deleteById(@RequestParam int cpf) {
        this.clienteRepository.deleteById(cpf);
        return ResponseEntity.ok().build();
    }
}
