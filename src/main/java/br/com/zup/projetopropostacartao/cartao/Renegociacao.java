package br.com.zup.projetopropostacartao.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "renegociacaoCartao")
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int quantidade;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDateTime dataDeCriacao;
    @ManyToOne
    private Cartao cartao;

    public Renegociacao(int quantidade, @NotNull BigDecimal valor, @NotNull LocalDateTime dataDeCriacao, Cartao cartao) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public String toString() {
        return "Renegociacao{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}
