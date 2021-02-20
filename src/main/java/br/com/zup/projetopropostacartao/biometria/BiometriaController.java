package br.com.zup.projetopropostacartao.biometria;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class BiometriaController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping("/cartoes/{id}/biometrias")
    public ResponseEntity<?> cadastra(@PathVariable Long id,
                                      @RequestBody @Valid BiometriaForm form,
                                      UriComponentsBuilder uriBuilder) {

        Cartao cartao = em.find(Cartao.class, id);
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }

        if(!form.estaEmBase64()){
            throw new IllegalStateException("Formato Inválido!");
        }
        Biometria biometria = new Biometria(cartao, form.getTexto());
        em.persist(biometria);

        URI location = uriBuilder.path("/cartoes/{idCartao}/biometrias/{idBiometria}").buildAndExpand(cartao.getId(), biometria.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}