package br.com.zup.projetopropostacartao.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bloqueios")
public class Bloqueio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @NotBlank
    private String sistemaReponsavel;
    @NotNull
    private boolean ativo;
    @ManyToOne
    private Cartao cartao;
    @NotBlank
    private  String enderecoIp;
    @NotBlank
    private String nome;

    @Deprecated
    Bloqueio() {}

    public Bloqueio(@NotBlank String sistemaReponsavel,
                    Cartao cartao, @NotBlank String enderecoIp, @NotBlank String nome) {
        this.sistemaReponsavel = sistemaReponsavel;
        this.cartao = cartao;
        this.enderecoIp = enderecoIp;
        this.nome = nome;
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

    public String getEnderecoIp() {
        return enderecoIp;
    }

    public String getNome() {
        return nome;
    }
}
