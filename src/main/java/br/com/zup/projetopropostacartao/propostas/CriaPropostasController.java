package br.com.zup.projetopropostacartao.propostas;

import br.com.zup.projetopropostacartao.feign.AnaliseClient;
import br.com.zup.projetopropostacartao.repositories.PropostaRepository;
import br.com.zup.projetopropostacartao.validators.PropostaDuplicadaValidator;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping("api/propostas")
public class CriaPropostasController {

    private final PropostaRepository novaPropostaRepository;
    private final AnaliseClient analiseClient;

    public CriaPropostasController(PropostaRepository novaPropostaRepository, AnaliseClient analiseClient) {
        this.novaPropostaRepository = novaPropostaRepository;
        this.analiseClient = analiseClient;
    }

    @Autowired
    private PropostaDuplicadaValidator propostaDuplicadaValidador;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(propostaDuplicadaValidador);
    }

    @PostMapping
    public ResponseEntity<?> criaPropostas(@RequestBody @Valid NovaPropostaRequest request,
                                           UriComponentsBuilder uriBuilder) {

        Proposta novaProposta = request.toModel();

        if (novaPropostaRepository.existsByDocumento(request.getDocumento())) {
            HashMap<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "Documento já cadastrado!");

            return ResponseEntity.unprocessableEntity().body(resposta);
        }

        novaProposta = novaPropostaRepository.save(novaProposta);

        try {
            AnaliseClient.ConsultaStatusResponse requisicaoDaAnalise =
                    analiseClient.consulta(new AnaliseClient.ConsultaStatusRequest(novaProposta));
            novaProposta.setStatus(Status.ELEGIVEL);
        } catch (FeignException.UnprocessableEntity e) {
            novaProposta.setStatus(Status.NAO_ELEGIVEL);
        }

        novaPropostaRepository.save(novaProposta);

        URI uri = uriBuilder.path("api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}