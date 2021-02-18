package br.com.zup.projetopropostacartao.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vencimentoCartao")
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int diaVencimento;
    @NotNull
    private LocalDateTime dataDeCriacao;
    @ManyToOne
    private Cartao cartao;

    public Vencimento(int diaVencimento, @NotNull LocalDateTime dataDeCriacao, Cartao cartao) {
        this.diaVencimento = diaVencimento;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
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

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public String toString() {
        return "Vencimento{" +
                "id=" + id +
                ", diaVencimento=" + diaVencimento +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}
