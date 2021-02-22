package br.com.zup.projetopropostacartao.bloqueios;

import br.com.zup.projetopropostacartao.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;
    @NotNull
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;

    @Deprecated
    Bloqueio () {}

    public Bloqueio(@NotNull Cartao cartao,
                    @NotBlank String ipCliente, @NotBlank String userAgent) {
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}