package br.com.zup.projetopropostacartao.avisos;

import java.net.URI;
import java.security.Principal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.feign.CartaoClient;
import br.com.zup.projetopropostacartao.repositories.CartaoRepository;
import feign.FeignException;

@RestController
public class AvisoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PersistenceContext
    private EntityManager manager;

    private static final Logger logger = LoggerFactory.getLogger(AvisoController.class);

    @Transactional
    @PostMapping("cartoes/{id}/avisos")
    public ResponseEntity<?> cadastraAviso(@PathVariable Long id,@RequestBody @Valid AvisoRequest avisoRequest,
    HttpServletRequest request, UriComponentsBuilder uriBuilder) {

        logger.info("Solicitando cartao do banco de dados.");
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Cartão de id {} não encontrado", id)));

        logger.info("Cartao de id {} encontrado", id);
        logger.info("Coletando informacoes de usuario logado.");

        Principal principal = request.getUserPrincipal();

        logger.info("Adicionando aviso de viagem ao cartao.");
        Aviso aviso = new Aviso(cartao, avisoRequest.getDestino(), avisoRequest.getValidoAte(),
        request.getRemoteAddr(),request.getHeader("User-Agent"));

        logger.info("Informando aviso de viagem ao sistema legado.");

        try {
            
            AvisoResponse response = cartaoClient.solicitaViagem(cartao.getNumeroCartao(), avisoRequest);

            logger.info("Salvando alterações do cartão no banco de dados");

            cartao.associaAvisos(aviso);

            manager.merge(cartao);

            logger.info("Aviso de viagem registrado com sucesso.");

        } catch(FeignException e) {
            logger.warn(e.getMessage());
                return ResponseEntity.unprocessableEntity().body("Erro inesperado ao informar o aviso de viagem ao sistema legado.");
        }

        URI uri = uriBuilder.path("api/cartao/{id}").buildAndExpand(cartao.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}