package br.com.zup.projetopropostacartao.services;

import br.com.zup.projetopropostacartao.propostas.Proposta;
import br.com.zup.projetopropostacartao.repositories.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private static final Logger logger = LoggerFactory.getLogger(PropostaService.class);

    @Autowired
    private PropostaRepository propostaRepository;

    public Proposta buscarPorId(String id) {
        logger.info("Buscando proposta por id");
        return (Proposta) propostaRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Proposta com id " + id + " não encontrada"));
    }
}
