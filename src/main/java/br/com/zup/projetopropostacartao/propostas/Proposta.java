package br.com.zup.projetopropostacartao.propostas;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import br.com.zup.projetopropostacartao.validators.CpfCnpj;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "propostas")
public class Proposta {

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

    @OneToOne(mappedBy = "proposta", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Cartao cartao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private boolean cartaoCriado;

    /*
    @deprecated deveria ser usado apenas por frameworks
    */
    @Deprecated
    Proposta() {}

    public Proposta(@NotBlank @NotNull String nome, @NotBlank @NotNull @Email String email,
                    @NotNull Endereco endereco, @NotBlank @NotNull String documento,
                    @NotNull @Positive BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.documento = documento;
        this.salario = salario;
        this.cartao = null;
        this.cartaoCriado = false;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public String getDocumento() {
        return documento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isCartaoCriado() {
        return cartaoCriado;
    }

    public void setCartaoCriado(boolean cartaoCriado) {
        this.cartaoCriado = cartaoCriado;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "NovaProposta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", endereco=" + endereco +
                ", documento='" + documento + '\'' +
                ", salario=" + salario +
                ", cartao=" + cartao +
                ", status=" + status +
                ", cartaoCriado=" + cartaoCriado +
                '}';
    }
}
