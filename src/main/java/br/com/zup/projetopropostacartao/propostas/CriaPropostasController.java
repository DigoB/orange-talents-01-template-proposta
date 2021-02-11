package br.com.zup.projetopropostacartao.propostas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/propostas")
public class CriaPropostasController {

    @Autowired
    private NovaPropostaRepository novaPropostaRepository;

    @PostMapping
    public ResponseEntity<NovaPropostaDto> criaPropostas(@RequestBody @Valid NovaPropostaRequest request,
                                                      UriComponentsBuilder uriBuilder) {

        NovaProposta novaProposta = request.toModel();

        novaPropostaRepository.save(novaProposta);

        URI uri = uriBuilder.path("api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}