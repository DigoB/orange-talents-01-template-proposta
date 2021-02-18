package br.com.zup.projetopropostacartao.cartao;

import br.com.zup.projetopropostacartao.validators.ValorUnico;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "carteirasCartao")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @ValorUnico(domainClass = Carteira.class, fieldName = "email")
    private String email;
    @NotNull
    private LocalDateTime associadoEm;
    @NotBlank
    private String emissor;
    @ManyToOne
    private Cartao cartao;

    public Carteira(@NotBlank @Email String email, @NotNull LocalDateTime associadoEm, @NotBlank String emissor,
                    Cartao cartao) {
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
        this.cartao = cartao;
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

    public Cartao getCartao() {
        return cartao;
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
}
