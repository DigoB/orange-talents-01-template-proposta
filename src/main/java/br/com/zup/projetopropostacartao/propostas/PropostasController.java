package br.com.zup.projetopropostacartao.propostas;

import br.com.zup.projetopropostacartao.feign.AnaliseClient;
import br.com.zup.projetopropostacartao.repositories.PropostaRepository;
import br.com.zup.projetopropostacartao.services.PropostaService;
import br.com.zup.projetopropostacartao.validators.PropostaDuplicadaValidator;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("api/propostas")
public class PropostasController {

    private final PropostaRepository propostaRepository;
    private final AnaliseClient analiseClient;
    private final PropostaService service;

    public PropostasController(PropostaRepository propostaRepository,
                               AnaliseClient analiseClient,
                               PropostaService service) {
        this.propostaRepository = propostaRepository;
        this.analiseClient = analiseClient;
        this.service = service;
    }

    @Autowired
    private PropostaDuplicadaValidator propostaDuplicadaValidador;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(propostaDuplicadaValidador);
    }

    @PostMapping
    public ResponseEntity<?> criaPropostas(@RequestBody @Valid PropostaRequest request,
                                           UriComponentsBuilder uriBuilder) {

        Proposta novaProposta = request.toModel();

        if (propostaRepository.existsByDocumento(request.getDocumento())) {
            HashMap<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "Documento já cadastrado!");

            return ResponseEntity.unprocessableEntity().body(resposta);
        }

        novaProposta = propostaRepository.save(novaProposta);

        try {
            AnaliseClient.ConsultaStatusResponse requisicaoDaAnalise =
                    analiseClient.consulta(new AnaliseClient.ConsultaStatusRequest(novaProposta));
            novaProposta.setStatus(Status.ELEGIVEL);
        } catch (FeignException.UnprocessableEntity e) {
            novaProposta.setStatus(Status.NAO_ELEGIVEL);
        }

        propostaRepository.save(novaProposta);

        URI uri = uriBuilder.path("api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> detalharProposta(@PathVariable @NotNull Long id) {

        Optional<Proposta> proposta = propostaRepository.findById(id);

        if (!proposta.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new PropostaResponse(proposta.get()));
        }
    }
}