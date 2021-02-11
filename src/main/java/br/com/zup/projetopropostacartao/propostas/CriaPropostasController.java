package br.com.zup.projetopropostacartao.propostas;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping("api/propostas")
public class CriaPropostasController {

    private final NovaPropostaRepository novaPropostaRepository;
    private final AnaliseClient analiseClient;

    public CriaPropostasController(NovaPropostaRepository novaPropostaRepository, AnaliseClient analiseClient) {
        this.novaPropostaRepository = novaPropostaRepository;
        this.analiseClient = analiseClient;
    }

    @PostMapping
    public ResponseEntity<?> criaPropostas(@RequestBody @Valid NovaPropostaRequest request,
                                           UriComponentsBuilder uriBuilder) {

        NovaProposta novaProposta = request.toModel();

        if (novaPropostaRepository.existsByDocumento(request.getDocumento())) {
            HashMap<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "Documento já cadastrado!");

            return ResponseEntity.unprocessableEntity().body(resposta);
        }
        novaPropostaRepository.save(novaProposta);

        try {
            AnaliseClient.ConsultaStatusRequest requisicaoDaAnalise = new AnaliseClient.ConsultaStatusRequest(novaProposta);
            novaProposta.setStatus(Status.ELEGIVEL);
        } catch (FeignException.UnprocessableEntity e) {
            novaProposta.setStatus(Status.NAO_ELEGIVEL);
        }

        novaPropostaRepository.save(novaProposta);

        URI uri = uriBuilder.path("api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}