package br.com.zup.projetopropostacartao.carteiras;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zup.projetopropostacartao.cartao.Cartao;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Cartao cartao;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;

    @Deprecated
    Carteira() {}


    public Carteira(String email, TipoCarteira carteira, Cartao cartao) {
        this.cartao = cartao;
        this.email = email;
        this.carteira = carteira;
    }   

    public Long getId() {
        return this.id;
    }

    public Cartao getCartao() {
        return this.cartao;
    }

    public String getEmail() {
        return this.email;
    }

    public TipoCarteira getCarteira() {
        return this.carteira;
    }

}
