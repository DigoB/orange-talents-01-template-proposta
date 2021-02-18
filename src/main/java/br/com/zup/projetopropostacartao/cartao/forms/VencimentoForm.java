package br.com.zup.projetopropostacartao.cartao.forms;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.cartao.Vencimento;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class VencimentoForm {

    private Long id;
    @NotNull
    private int diaVencimento;
    @NotNull
    private LocalDateTime dataDeCriacao;

    public VencimentoForm(@NotNull int diaVencimento, @NotNull LocalDateTime dataDeCriacao) {
        this.diaVencimento = diaVencimento;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Long getId() {
        return id;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    @Override
    public String toString() {
        return "Vencimento{" +
                "id=" + id +
                ", diaVencimento=" + diaVencimento +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }

    public Vencimento toVencimento(Cartao cartao) {
        return new Vencimento(diaVencimento,dataDeCriacao, cartao);
    }
}