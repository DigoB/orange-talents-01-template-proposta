package br.com.zup.projetopropostacartao.avisos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.projetopropostacartao.cartao.Cartao;


@Entity
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private Cartao cartao;
    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate dataTerminoViagem;
    @NotNull
    private LocalDateTime avisadoEm = LocalDateTime.now();
    @NotBlank
    private String enderecoIp;
    @NotBlank
    private String sistemaResponsavel;

    @Deprecated
    Aviso() {}

    public Aviso(Cartao cartao,String destino, LocalDate dataTerminoViagem, String enderecoIp, String sistemaResponsavel) {
        this.cartao = cartao;
        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
        this.enderecoIp = enderecoIp;
        this.sistemaResponsavel = sistemaResponsavel;
    }


    public Long getId() {
        return this.id;
    }

    public Cartao getCartao() {
        return this.cartao;
    }


    public String getDestino() {
        return this.destino;
    }


    public LocalDate getDataTerminoViagem() {
        return this.dataTerminoViagem;
    }

    public LocalDateTime getAvisadoEm() {
        return this.avisadoEm;
    }

    public String getEnderecoIp() {
        return this.enderecoIp;
    }

    public String getSistemaResponsavel() {
        return this.sistemaResponsavel;
    }
    
}
