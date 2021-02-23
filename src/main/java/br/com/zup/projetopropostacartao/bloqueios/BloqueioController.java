package br.com.zup.projetopropostacartao.bloqueios;

import br.com.zup.projetopropostacartao.cartao.Bloqueio;
import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.feign.CartaoClient;
import br.com.zup.projetopropostacartao.repositories.CartaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;


@RestController
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoClient cartaoClient;

    @PersistenceContext
    private EntityManager manager;

    private static final Logger logger = LoggerFactory.getLogger(BloqueioController.class);

    @Transactional
    @PutMapping("/cartoes/{id}/bloqueios")
    public ResponseEntity<?> solicitaBloqueio(@PathVariable Long id, HttpServletRequest request) {

        logger.info("Solicitando cartao do banco de dados.");
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Cartão de id {} não encontrado", id)));

        if (cartao.isBloqueado()) {
            logger.info("Cartao bloqueado com sucesso.");
            logger.info("Atualizando cartao no banco de dados.");
            return ResponseEntity.unprocessableEntity().build();
        }

        logger.info("Cartao de id {} encontrado", id);
        logger.info("Coletando informacoes de usuario logado.");

        Principal principal = request.getUserPrincipal();
        Bloqueio bloqueio = new Bloqueio("propostas",cartao,request.getRemoteAddr(),
                request.getHeader("User-Agent"));
        cartao.bloquear(bloqueio);

        logger.info("Solicitacao de bloqueio adicionada ao cartao.");

        try {
            logger.info("Solicitando bloqueio para o sistema legado.");
            BloqueioResponse response = cartaoClient.bloqueio(cartao.getNumeroCartao(), new BloqueioRequest("proposta"));
            cartao.bloquear();
            cartao.associaBloqueio(bloqueio);

        } catch (FeignException e) {
//            if (e.status() == 422) {
                logger.warn(e.getMessage());
                return ResponseEntity.unprocessableEntity().build();
        }

        manager.merge(cartao);
//        }

        logger.info("Retornando cartao.");

        return ResponseEntity.ok().build();
    }
}