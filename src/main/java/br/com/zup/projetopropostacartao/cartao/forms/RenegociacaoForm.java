package br.com.zup.projetopropostacartao.cartao.forms;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.cartao.Renegociacao;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoForm {

    private Long id;
    @NotNull
    private int quantidade;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDateTime dataDeCriacao;

    public RenegociacaoForm(@NotNull int quantidade, @NotNull BigDecimal valor, @NotNull LocalDateTime dataDeCriacao) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
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

    @Override
    public String toString() {
        return "Renegociacao{" +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }

    public Renegociacao toRenegociacao(Cartao cartao) {
        return new Renegociacao(quantidade,valor,dataDeCriacao, cartao);
    }
}