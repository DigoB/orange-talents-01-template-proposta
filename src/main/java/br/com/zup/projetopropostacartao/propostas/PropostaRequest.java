package br.com.zup.projetopropostacartao.propostas;

import br.com.zup.projetopropostacartao.validators.CpfCnpj;
import br.com.zup.projetopropostacartao.validators.ValorUnico;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    private String nome;
    @NotBlank @Email @ValorUnico(domainClass = Proposta.class,fieldName = "email")
    private String email;
    @NotBlank  @CpfCnpj
    private String documento;
    @NotNull @Positive
    private BigDecimal salario;
    @NotNull
    private EnderecoRequest endereco;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public boolean documentoValido() {
        Assert.hasLength(documento, "Você não deveria validar o documento se ele não estiver preenchido");
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }

    @Override
    public String toString() {
        return "NovaPropostaRequest{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    public Proposta toModel() {
        return new Proposta(nome,email,
                new Endereco(endereco.getLogradouro(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getCep()),documento,salario);
    }
}
