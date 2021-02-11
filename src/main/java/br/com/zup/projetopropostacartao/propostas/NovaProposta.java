package br.com.zup.projetopropostacartao.propostas;

import br.com.zup.projetopropostacartao.validators.CpfCnpj;
import br.com.zup.projetopropostacartao.validators.ValorUnico;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotBlank
    @NotNull
    @CpfCnpj
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    private String documento;
    @NotNull
    @Embedded
    private Endereco endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    /*
    @deprecated deveria ser usado apenas por frameworks
    */
    @Deprecated
    NovaProposta() {
    }

    public NovaProposta(@NotBlank @NotNull String nome, @NotBlank @NotNull @Email String email,
                        @NotBlank @NotNull @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})") String documento,
                        @NotNull Endereco endereco, @NotNull @Positive BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
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
}
