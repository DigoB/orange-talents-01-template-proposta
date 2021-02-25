package br.com.zup.projetopropostacartao.carteiras;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoCarteira carteira;


    public CarteiraRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }


    public String getEmail() {
        return this.email;
    }

    public TipoCarteira getCarteira() {
        return this.carteira;
    }

}
