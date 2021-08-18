package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.PontoColeta;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
import br.com.letscode.coleta_consciente.excecoes.NotFoundException;
import br.com.letscode.coleta_consciente.repository.PontoColetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/ponto_coleta")
public class PontoColetaRestController {

    private final PontoColetaRepository pontoColetaRepository;

    @PostMapping("cadastrar")
    public ResponseEntity<PontoColeta> create(@RequestBody PontoColeta pontoColeta,
                                              UriComponentsBuilder uriComponentsBuilder) {
        if (this.pontoColetaRepository.findById(pontoColeta.getCnpj()).isEmpty()){
            this.pontoColetaRepository.save(pontoColeta);
            URI uri = uriComponentsBuilder.buildAndExpand(pontoColeta).toUri();
            return ResponseEntity.created(uri).body(pontoColeta);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<PontoColeta> atualizar(@RequestBody PontoColeta pontoColeta) {
        if (this.pontoColetaRepository.findById(pontoColeta.getCnpj()).isPresent()){
            this.pontoColetaRepository.save(pontoColeta);
            return ResponseEntity.status(HttpStatus.OK).body(pontoColeta);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping ("/deletar")
    public ResponseEntity<?> deleteById(@RequestParam int cnpj) {
        var empresa = this.pontoColetaRepository.findById(cnpj);
        if (empresa.isPresent()) {
            empresa.get().setStatus(false);
            this.pontoColetaRepository.save(empresa.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/buscar_cnpj")
    public ResponseEntity<Optional<PontoColeta>> findById(@RequestParam int cnpj){
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findById(cnpj));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar_todos")
    public List<PontoColeta> findAll(){
        return this.pontoColetaRepository.findAll();
    }

    @GetMapping("/buscar_estado")
    public ResponseEntity<List<PontoColeta>> findByEstado(@RequestParam Estados estado) {
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findByEstado(estado));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar_residuo")
    public ResponseEntity<List<PontoColeta>> findByResiduo(@RequestParam TipoResiduo tipoResiduo){
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findByTipoResiduo(tipoResiduo));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar_tipo_empresa")
    public ResponseEntity<List<PontoColeta>> findByResiduo(@RequestParam TipoEmpresa tipoEmpresa){
        try {
            return ResponseEntity.ok()
                    .body(this.pontoColetaRepository.findByTipoEmpresa(tipoEmpresa));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar_preco")
    public ResponseEntity<List<PontoColeta>> findByPreco(@RequestParam int preco){
        try {
            var lista = this.pontoColetaRepository.findAll();
            return ResponseEntity.ok()
                    .body(lista.stream().filter(l -> l.getPreco() <= preco).collect(Collectors.toList()));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
