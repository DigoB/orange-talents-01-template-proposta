package br.com.zup.projetopropostacartao.bloqueios;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.feign.CartaoClient;
import br.com.zup.projetopropostacartao.repositories.BloqueioRepository;
import br.com.zup.projetopropostacartao.repositories.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class BloqueioController {

    private final String sistema = "projeto-proposta";

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PutMapping("/cartoes/{id}/bloqueio")
    public ResponseEntity<?> bloqueio(@PathVariable Long id, HttpServletRequest request) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if (possivelCartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // Continuar as regras de negocio do bloqueio

}