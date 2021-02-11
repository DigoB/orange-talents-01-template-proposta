package br.com.zup.projetopropostacartao.propostas;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    @NotBlank
    private String complemento;

    /*
        @deprecated deveria ser usado apenas por frameworks
     */
    @Deprecated
    Endereco() {}

    public Endereco(@NotBlank String cep, @NotBlank String logradouro, @NotBlank String numero, @NotBlank String complemento) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }
}
