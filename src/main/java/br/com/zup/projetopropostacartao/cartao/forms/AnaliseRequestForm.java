package br.com.zup.projetopropostacartao.cartao.forms;

public class AnaliseRequestForm {

    private String documento;
    private String nome;
    private Long idProposta;

    public AnaliseRequestForm(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return this.documento;
    }

    public String getNome() {
        return this.nome;
    }

    public Long getIdProposta() {
        return this.idProposta;
    }

}