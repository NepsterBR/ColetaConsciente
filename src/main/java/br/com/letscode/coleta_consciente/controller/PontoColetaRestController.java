package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.PontoColeta;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.repository.PontoColetaRepository;
import br.com.letscode.coleta_consciente.request.PontoColetaRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/ponto-coleta")
public class PontoColetaRestController {

    private final PontoColetaRepository pontoColetaRepository;

    @PostMapping("cadastrar")
    public ResponseEntity<PontoColetaRequest> create(@RequestBody PontoColetaRequest pontoColetaRequest,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        var pontoColeta = pontoColetaRequest.convert();
        if (this.pontoColetaRepository.findById(pontoColeta.getCnpj()).isEmpty()){
            this.pontoColetaRepository.save(pontoColeta);
            URI uri = uriComponentsBuilder.buildAndExpand(pontoColeta).toUri();
            return ResponseEntity.created(uri).body(pontoColetaRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<PontoColetaRequest> atualizar(@RequestBody PontoColetaRequest pontoColetaRequest) {
        var pontoColeta = pontoColetaRequest.convert();
        if (this.pontoColetaRepository.findById(pontoColeta.getCnpj()).isPresent()){
            this.pontoColetaRepository.save(pontoColeta);
            return ResponseEntity.status(HttpStatus.OK).body(pontoColetaRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping ("/deletar")
    public ResponseEntity<?> deleteById(@RequestParam int cnpj) {
        var empresa = this.pontoColetaRepository.findById(cnpj);
        try {
            if (empresa.isPresent()) {
                empresa.get().setStatus(false);
                this.pontoColetaRepository.save(empresa.get());
                return ResponseEntity.ok().build();
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscar-cnpj")
    public ResponseEntity<Optional<PontoColeta>> findById(@RequestParam int cnpj){
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findById(cnpj));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar-todos")
    public List<PontoColeta> findAll(){
        return this.pontoColetaRepository.findAll();
    }

    @GetMapping("/buscar-estado")
    public ResponseEntity<List<PontoColeta>> findByEstado(@RequestParam Estados estado) {
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findByEstado(estado));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar-residuo")
    public ResponseEntity<List<PontoColeta>> findByResiduo(@RequestParam TipoResiduo tipoResiduo){
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findByTipoResiduo(tipoResiduo));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar-tipo-empresa")
    public ResponseEntity<List<PontoColeta>> findByResiduo(@RequestParam TipoEmpresa tipoEmpresa){
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findByTipoEmpresa(tipoEmpresa));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar-preco")
    public ResponseEntity<List<PontoColeta>> findByPreco(@RequestParam int preco){
        try {
            var lista = this.pontoColetaRepository.findAll();
            return ResponseEntity.ok()
                    .body(lista.stream().filter(l -> l.getPreco() <= preco).collect(Collectors.toList()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
