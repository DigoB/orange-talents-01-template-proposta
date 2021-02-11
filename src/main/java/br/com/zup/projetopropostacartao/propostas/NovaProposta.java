package br.com.zup.projetopropostacartao.propostas;

import br.com.zup.projetopropostacartao.validators.CpfCnpj;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "propostas")
public class NovaProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    @Embedded
    @Column(nullable = false)
    private Endereco endereco;
    @NotBlank
    @NotNull
    @CpfCnpj
    @Column(nullable = false)
    private String documento;
    @NotNull
    @Positive
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private Status status;


    /*
    @deprecated deveria ser usado apenas por frameworks
    */
    @Deprecated
    NovaProposta() {}

    public NovaProposta(@NotBlank @NotNull String nome, @NotBlank @NotNull @Email String email,
                        @NotBlank String documento, @NotNull @Positive BigDecimal salario, @NotNull Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.documento = documento;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Status getStatus() {
        return status;
    }

    public void atualizaStatus(String solicitacao) {
        this.status = status.resultadoPara(solicitacao);
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
