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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;;

@AllArgsConstructor
@RestController
@RequestMapping("/ponto_coleta")
public class PontoColetaController {

    private final PontoColetaRepository pontoColetaRepository;

    @PostMapping("cadastrar")
    public ResponseEntity<PontoColeta> create(@RequestBody PontoColeta pontoColeta,
                                              UriComponentsBuilder uriComponentsBuilder) {
        if (this.pontoColetaRepository.findById(pontoColeta.getCnpj()).isEmpty()){
            this.pontoColetaRepository.save(pontoColeta);
            URI uri = uriComponentsBuilder.buildAndExpand(pontoColeta.getCnpj()).toUri();
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
    public ResponseEntity<?> deleteall(@RequestParam int cnpj) {
        this.pontoColetaRepository.deleteById(cnpj);
        return ResponseEntity.ok().build();
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

    @GetMapping("/buscar_empresa")
    public List<PontoColeta> findByResiduo(@RequestParam TipoEmpresa tipoEmpresa){
        return this.pontoColetaRepository.findByTipoEmpresa(tipoEmpresa);
    }

    @GetMapping("/buscar_preco")
    public List<PontoColeta> findByPreco(@RequestParam int preco){
        var lista = this.pontoColetaRepository.findAll();
//        List<PontoColeta> pontoColeta  = lista
//                .stream().filter(l -> l.getPreco() <= preco).collect(Collectors.toList());

        return lista.stream().filter(l -> l.getPreco() <= preco).collect(Collectors.toList());

//        public List<String> getFilteredList(List<Foo> fooList) {
//            return fooList.stream().filter(f -> "someword".compareTo(f.getName()) > 0).collect(Collectors.toList());
//        }



//                .filter(c -> c.() < preco). collect(Collectors.toList());


//        var lista = this.pontoColetaRepository.findAll();
//        var listaTemp = new ArrayList<PontoColeta>();
//        for (int i=0; i <= lista.size(); i++){
//            if (lista.get(i).getPreco() <= preco){
//                listaTemp.add(lista.get(i));
//            }
//        }
//        lista = listaTemp;
//        return pontoColeta;
    }

}
