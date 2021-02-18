package br.com.zup.projetopropostacartao.services;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.cartao.forms.CartaoResponse;
import br.com.zup.projetopropostacartao.feign.CartaoClient;
import br.com.zup.projetopropostacartao.propostas.Proposta;
import br.com.zup.projetopropostacartao.repositories.CartaoRepository;
import br.com.zup.projetopropostacartao.repositories.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Component
public class CartaoService {

    private static final Logger logger = LoggerFactory.getLogger(CartaoService.class);

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoClient cartaoClient;

    @PersistenceContext
    private EntityManager manager;

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void verificarCartao() {
        logger.info("Iniciando verificarCartao() Async");

        Collection<Proposta> propostas = propostaRepository.findByCartaoNaoCriado();

        logger.info("Numero de propostas encontradas: {}", propostas.size());

        propostas.forEach(proposta -> {

                try {
                    CartaoResponse cartaoCriado = cartaoClient.solicitaCartao(proposta.getId());
                    System.out.println(cartaoCriado);

                    logger.info("Cartão encontrado para proposta {}", proposta.getId());
                    logger.info("Cartao recebido: {}", cartaoCriado.toString());

                    Cartao cartao = cartaoCriado.toCartao(proposta);

                    logger.info("Objeto de cartao criado.");


                    logger.info("Cartão persistido para proposta");
                    manager.persist(cartao);
                    logger.info("Cartão salvo no banco de dados");
                    proposta.setCartaoCriado(true);
                    manager.merge(proposta);
                    logger.info("Proposta atualizada no banco de dados");
                } catch (FeignException e) {
                    logger.info("Deu ruim, proposta {}", proposta.getId());
                    logger.info(e.getMessage());
                }
        });

        logger.info("verificaCartao() finalizado.");
    }
}