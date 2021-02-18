package br.com.zup.projetopropostacartao.cartao.forms;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.cartao.Carteira;
import br.com.zup.projetopropostacartao.validators.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CarteirasForm {

    private Long id;
    @NotBlank
    @Email
    @ValorUnico(domainClass = Carteira.class, fieldName = "email")
    private String email;
    @NotNull
    private LocalDateTime associadoEm;
    @NotBlank
    private String emissor;

    public CarteirasForm(@NotBlank @Email String email, @NotNull LocalDateTime associadoEm, @NotBlank String emissor) {
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadoEm() {
        return associadoEm;
    }

    public String getEmissor() {
        return emissor;
    }

    @Override
    public String toString() {
        return "Carteiras{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", associadoEm=" + associadoEm +
                ", emissor='" + emissor + '\'' +
                '}';
    }

    public Carteira toCarteiraDigital(Cartao cartao) {
        return new Carteira(email,associadoEm,emissor, cartao);
    }
}