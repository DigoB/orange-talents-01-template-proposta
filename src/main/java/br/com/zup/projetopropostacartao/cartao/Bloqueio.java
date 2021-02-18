package br.com.zup.projetopropostacartao.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bloqueiosCartao")
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime bloqueadoEm;
    @NotBlank
    private String sistemaReponsavel;
    @NotNull
    private boolean ativo;
    @ManyToOne
    private Cartao cartao;

    public Bloqueio(@NotNull LocalDateTime bloqueadoEm, @NotBlank String sistemaReponsavel, boolean ativo, Cartao cartao) {
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaReponsavel = sistemaReponsavel;
        this.ativo = ativo;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaReponsavel() {
        return sistemaReponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public String toString() {
        return "Bloqueios{" +
                "id=" + id +
                ", bloqueadoEm=" + bloqueadoEm +
                ", sistemaReponsavel='" + sistemaReponsavel + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
