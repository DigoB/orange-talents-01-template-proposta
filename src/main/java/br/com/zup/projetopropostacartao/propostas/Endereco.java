package br.com.zup.projetopropostacartao.propostas;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

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

    public Endereco(@NotBlank String cep, @NotBlank String logradouro, @NotBlank String numero, @NotBlank String complemento, @NotNull @Positive BigDecimal salario) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }
}
