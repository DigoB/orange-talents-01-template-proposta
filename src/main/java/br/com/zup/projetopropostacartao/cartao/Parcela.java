package br.com.zup.projetopropostacartao.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "parcelasCartao")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int quantidade;
    @NotNull
    private BigDecimal valor;
    @ManyToOne
    private Cartao cartao;

    public Parcela(Long id, int quantidade, @NotNull BigDecimal valor, Cartao cartao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
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

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public String toString() {
        return "Parcelas{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                '}';
    }
}
