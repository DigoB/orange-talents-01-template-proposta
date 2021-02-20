package br.com.zup.projetopropostacartao.biometria;

import br.com.zup.projetopropostacartao.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.constraints.NotBlank;

public class BiometriaForm {

    @NotBlank
    private String texto;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)

    public BiometriaForm(@NotBlank String texto) {
        this.texto = texto;
    }

    public boolean estaEmBase64() {
        return Base64.isBase64(texto.getBytes());
    }

    public String getTexto() {
        return texto;
    }
}
