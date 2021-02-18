package br.com.zup.projetopropostacartao.propostas;

import br.com.zup.projetopropostacartao.propostas.Proposta;
import br.com.zup.projetopropostacartao.validators.ExistsId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AnaliseClienteRequest {

    @NotBlank
    private String documento;
    @NotBlank
    private String titular;
    @NotNull
    private Long idProposta;

    public AnaliseClienteRequest(@NotBlank String documento, @NotBlank String titular, @NotNull Long idProposta) {
        this.documento = documento;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    @Override
    public String toString() {
        return "NovoCartaoRequest{" +
                "documento='" + documento + '\'' +
                ", titular='" + titular + '\'' +
                ", idProposta=" + idProposta +
                '}';
    }
}