package br.com.zup.projetopropostacartao.cartao.forms;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.cartao.Parcela;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ParcelasForm {

    private Long id;
    @NotNull
    private int quantidade;
    @NotNull
    private BigDecimal valor;

    public ParcelasForm(Long id, @NotNull int quantidade, @NotNull BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
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

    @Override
    public String toString() {
        return "Parcelas{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                '}';
    }

    public Parcela toParcela(Cartao cartao) {
        return new Parcela(id,quantidade,valor, cartao);
    }
}