package br.com.zup.projetopropostacartao.cartao.forms;

import br.com.zup.projetopropostacartao.cartao.Bloqueio;
import br.com.zup.projetopropostacartao.cartao.Cartao;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BloqueiosForm {

    @Id
    private Long id;
    @NotNull
    private LocalDateTime bloqueadoEm;
    @NotBlank
    private String sistemaReponsavel;
    @NotNull
    private boolean ativo;

    public BloqueiosForm(@NotNull LocalDateTime bloqueadoEm, @NotBlank String sistemaReponsavel, @NotNull boolean ativo) {
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaReponsavel = sistemaReponsavel;
        this.ativo = ativo;
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

    @Override
    public String toString() {
        return "Bloqueios{" +
                ", bloqueadoEm=" + bloqueadoEm +
                ", sistemaReponsavel='" + sistemaReponsavel + '\'' +
                ", ativo=" + ativo +
                '}';
    }

    public Bloqueio toCartaoBloqueio(Cartao cartao) {
        return new Bloqueio(bloqueadoEm,sistemaReponsavel,isAtivo(), cartao);
    }
}