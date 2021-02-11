package br.com.zup.projetopropostacartao.propostas;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;

    public EnderecoRequest(@NotBlank String cep, @NotBlank String logradouro,
                           @NotBlank String numero, String complemento) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }
}
