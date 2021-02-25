package br.com.zup.projetopropostacartao.carteiras;

import java.net.URI;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.feign.CartaoClient;
import br.com.zup.projetopropostacartao.repositories.CartaoRepository;
import br.com.zup.projetopropostacartao.repositories.CarteiraRepository;
import feign.FeignException;

@RestController
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @PersistenceContext
    private EntityManager manager;
    

    private static final Logger logger = LoggerFactory.getLogger(CarteiraController.class);

    @Transactional
    @PostMapping("cartoes/{id}/carteiras")
    public ResponseEntity<?> cadastraCarteira(@PathVariable Long id, @RequestBody @Valid CarteiraRequest carteiraRequest,
    HttpServletRequest request, UriComponentsBuilder uriBuilder) {

        logger.info("Solicitando cartao do banco de dados.");

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = possivelCartao.get();

        logger.info("Cartao de id {} encontrado", id);

        logger.info("Informando associacao ao sistema legado");

        try {

             cartaoClient.adiciona(cartao.getNumeroCartao(), carteiraRequest);

             logger.info("Salvando informacoes no banco de dados");

             Carteira carteira = carteiraRepository.save(new Carteira(carteiraRequest.getEmail(),carteiraRequest.getCarteira(),
             cartao));

            logger.info("Carteira associada com sucesso!");

            URI uri = uriBuilder.path("cartoes/{id}/carteiras/{id}").build(cartao.getId(),carteira.getId());

            return ResponseEntity.created(uri).build();

        }
        catch (FeignException e) {
            logger.warn(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}