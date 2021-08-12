package br.com.letscode.coleta_consciente.controller;

import br.com.letscode.coleta_consciente.entity.PontoColeta;
import br.com.letscode.coleta_consciente.entity.enuns.Estados;
import br.com.letscode.coleta_consciente.entity.enuns.TipoEmpresa;
import br.com.letscode.coleta_consciente.entity.enuns.TipoResiduo;
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

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deleteById(@RequestParam int cnpj) {
        this.pontoColetaRepository.deleteById(cnpj);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar_cnpj")
    public ResponseEntity<Optional<PontoColeta>> findById(@RequestParam int cnpj){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.pontoColetaRepository.findById(cnpj));
    }

    @GetMapping("/buscar_todos")
    public List<PontoColeta> findAll(){
        return this.pontoColetaRepository.findAll();
    }

    @GetMapping("/buscar_estado")
    public List<PontoColeta> findByEstado(@RequestParam Estados estado) {
        return this.pontoColetaRepository.findByEstado(estado);
    }

    @GetMapping("/buscar_residuo")
    public List<PontoColeta> findByResiduo(@RequestParam TipoResiduo tipoResiduo){
        return this.pontoColetaRepository.findByTipoResiduo(tipoResiduo);
    }

    @GetMapping("/buscar_tipo_empresa")
    public List<PontoColeta> findByResiduo(@RequestParam TipoEmpresa tipoEmpresa){
        return this.pontoColetaRepository.findByTipoEmpresa(tipoEmpresa);
    }

    @GetMapping("/buscar_preco")
    public List<PontoColeta> findByPreco(@RequestParam int preco){
        var lista = this.pontoColetaRepository.findAll();
        return lista.stream().filter(l -> l.getPreco() <= preco).collect(Collectors.toList());
    }

}
