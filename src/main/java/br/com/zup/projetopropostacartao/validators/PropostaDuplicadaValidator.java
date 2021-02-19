package br.com.zup.projetopropostacartao.validators;

import br.com.zup.projetopropostacartao.propostas.PropostaRequest;
import br.com.zup.projetopropostacartao.propostas.Proposta;
import br.com.zup.projetopropostacartao.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PropostaDuplicadaValidator implements Validator {

    @Autowired
    private PropostaRepository propostaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return PropostaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        PropostaRequest request = (PropostaRequest) target;

        Optional<Proposta> proposta = propostaRepository.findByDocumento(request.getDocumento());

        if(proposta.isPresent()) {
            throw new PropostaDuplicadaException("Proposta já existe para o solicitante.");
        }
    }

}