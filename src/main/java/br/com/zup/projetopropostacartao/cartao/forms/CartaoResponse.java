package br.com.zup.projetopropostacartao.cartao.forms;

import br.com.zup.projetopropostacartao.cartao.*;
import br.com.zup.projetopropostacartao.propostas.Proposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class CartaoResponse {


    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;

//    private Set<BloqueiosForm> bloqueios;
//    private Set<AvisosForm> avisos;
//    private Set<CarteirasForm> carteiras;
//    private Set<ParcelasForm> parcelas;
//    private RenegociacaoForm renegociacao;
//    private VencimentoForm vencimento;

    private String idProposta;

    public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, Set<BloqueiosForm> bloqueios,
//                          Set<AvisosForm> avisos, Set<CarteirasForm> carteiras, Set<ParcelasForm> parcelas,
                            BigDecimal limite, RenegociacaoForm renegociacao, VencimentoForm vencimento,
                          String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
//        this.bloqueios = bloqueios;
//        this.avisos = avisos;
//        this.carteiras = carteiras;
//        this.parcelas = parcelas;
        this.limite = limite;
//        this.renegociacao = renegociacao;
//        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getIdProposta() {
        return this.idProposta;
    }

    public String getId() {
        return this.id;
    }

    public LocalDateTime getEmitidoEm() {
        return this.emitidoEm;
    }

    public String getTitular() {
        return this.titular;
    }

    //    public Set<BloqueiosForm> getBloqueios() {
//        return this.bloqueios;
//    }
//
//    public Set<AvisosForm> getAvisos() {
//        return this.avisos;
//    }
//
//    public Set<CarteirasForm> getCarteiras() {
//        return this.carteiras;
//    }
//
//    public Set<ParcelasForm> getParcelas() {
//        return this.parcelas;
//    }
//
//    public BigDecimal getLimite() {
//        return this.limite;
//    }
//
//    public RenegociacaoForm getRenegociacao() {
//        return this.renegociacao;
//    }
//
//    public VencimentoForm getVencimento() {
//        return this.vencimento;
//    }
//
    public Cartao toCartao(Proposta proposta) {
        return new Cartao(this.id, this.emitidoEm, this.titular,proposta);
    }
}