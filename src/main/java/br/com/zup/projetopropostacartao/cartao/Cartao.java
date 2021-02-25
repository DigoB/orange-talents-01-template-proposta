package br.com.zup.projetopropostacartao.cartao;

import br.com.zup.projetopropostacartao.avisos.Aviso;
import br.com.zup.projetopropostacartao.biometria.Biometria;
import br.com.zup.projetopropostacartao.carteiras.Carteira;
import br.com.zup.projetopropostacartao.propostas.Proposta;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    private String titular;

    private BigDecimal limite;

    @NotNull
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Proposta proposta;

    @NotNull
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "cartao",cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusBloqueio statusBloqueio = StatusBloqueio.DESBLOQUEADO;

    @NotNull
    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Aviso> avisos = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "cartao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Collection<Carteira> carteiras = new ArrayList<>();


//    @Enumerated(EnumType.STRING)
//    private StatusBloqueio status = StatusBloqueio.DESBLOQUEADO;
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

    public BigDecimal getLimite() {
        return this.limite;
    }

    public Collection<Bloqueio> getBloqueios() {
        return this.bloqueios;
    }

    public Proposta getProposta() {
        return this.proposta;
    }

    public Collection<Aviso> getAvisos() {
        return this.avisos;
    }

    public void bloquear() {
        StatusBloqueio statusBloqueio = StatusBloqueio.BLOQUEADO;
    }

    public void bloquear(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
    }

    public boolean isBloqueado() {
        return this.statusBloqueio.equals(StatusBloqueio.BLOQUEADO);
    }

    public void associaBloqueio(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
    }

    public void associaAvisos(Aviso aviso) {
        this.avisos.add(aviso);
    }

   public Collection<Carteira> getCarteiras() {
       return this.carteiras;
   }

   public void associaCarteiras(Carteira carteira) {
    this.carteiras.add(carteira);
    }

}



//
//    public Collection<Parcela> getParcelas() {
//        return this.parcelas;
//    }
//
//
//    public Renegociacao getRenegociacao() {
//        return this.renegociacao;
//    }
//
//    public Vencimento getVencimento() {
//        return this.vencimento;
//    }
//

