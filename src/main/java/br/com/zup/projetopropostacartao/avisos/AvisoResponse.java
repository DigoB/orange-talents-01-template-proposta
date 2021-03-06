package br.com.zup.projetopropostacartao.avisos;

import javax.validation.constraints.NotBlank;

public class AvisoResponse {

    private StatusAviso statusAviso;

    @NotBlank
    private String resultado;

    @Deprecated
    public AvisoResponse() {
    }


    public AvisoResponse(String resultado) {
        this.resultado = resultado;
    }


    public String getResultado() {
        return this.resultado;
    }
}
