package br.com.zup.projetopropostacartao.propostas;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaPropostaDto {

    @NotBlank
    @NotNull
    private String nome;
    @NotBlank @NotNull @Email
    private String email;

    public NovaPropostaDto(NovaProposta novaProposta) {
        this.nome = novaProposta.getNome();
        this.email = novaProposta.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
