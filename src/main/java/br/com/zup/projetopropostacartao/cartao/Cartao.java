package br.com.zup.projetopropostacartao.cartao;

import br.com.zup.projetopropostacartao.biometria.Biometria;
import br.com.zup.projetopropostacartao.propostas.Proposta;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String numeroCartao;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotNull
    private String titular;

    private BigDecimal limite;

    @NotNull
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "cartao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Collection<Bloqueio> bloqueios = new ArrayList<>();

//    @Enumerated(EnumType.STRING)
//    private StatusBloqueio status = StatusBloqueio.DESBLOQUEADO;
//    @NotNull
//    @OneToMany(mappedBy = "cartao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    private Collection<Aviso> avisos = new ArrayList<>();
//    @NotNull
//    @OneToMany(mappedBy = "cartao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    private Collection<Carteira> carteiras = new ArrayList<>();
//    @NotNull
//    @OneToMany(mappedBy = "cartao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    private Collection<Parcela> parcelas = new ArrayList<>();
//
//    @ManyToOne
//    private Renegociacao renegociacao;
//    @NotNull
//    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    private Vencimento vencimento;

    @Deprecated
    Cartao() {
    }

    public Cartao(@NotNull String numeroCartao, LocalDateTime emitidoEm, String titular,
                  Proposta proposta) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.proposta = proposta;


//        this.bloqueios = bloqueios.stream().map(bloqueio -> bloqueio.toCartaoBloqueio(this))
//                .collect(Collectors.toList());
//        this.avisos = avisos.stream().map(aviso -> aviso.toCartaoAviso(this)).collect(Collectors.toList());
//        this.carteiras = carteiras.stream().map(carteira -> carteira.toCarteiraDigital(this))
//                .collect(Collectors.toList());
//        this.parcelas = parcelas.stream().map(parcela -> parcela.toParcela(this)).collect(Collectors.toList());
//        this.limite = limite;
//        this.renegociacao = renegociacao == null ? null : renegociacao.toRenegociacao(this);
//        this.vencimento = vencimento.toVencimento(this);

    }

    public Long getId() {
        return this.id;
    }

    public LocalDateTime getEmitidoEm() {
        return this.emitidoEm;
    }

    public String getTitular() {
        return this.titular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Collection<Bloqueio> getBloqueios() {
        return this.bloqueios;
    }

    public Proposta getProposta() {
        return this.proposta;
    }






//
//    public void addBloqueios(Bloqueio bloqueio) {
//        this.bloqueios.add(bloqueio);
//    }
//
//    public Collection<Aviso> getAvisos() {
//        return this.avisos;
//    }
//
//    public Collection<Carteira> getCarteiras() {
//        return this.carteiras;
//    }
//
//    public Collection<Parcela> getParcelas() {
//        return this.parcelas;
//    }
//
//    public BigDecimal getLimite() {
//        return this.limite;
//    }
//
//    public Renegociacao getRenegociacao() {
//        return this.renegociacao;
//    }
//
//    public Vencimento getVencimento() {
//        return this.vencimento;
//    }
//
//    public void addAvisos(Aviso aviso) {
//        this.avisos.add(aviso);
//    }
//    public void addCarteiras(Carteira carteira) {
//        this.carteiras.add(carteira);
//    }
//    public void bloquear() {
//        status = StatusBloqueio.BLOQUEADO;
//    }
}
