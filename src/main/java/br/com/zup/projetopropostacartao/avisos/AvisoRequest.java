package br.com.zup.projetopropostacartao.avisos;

import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvisoRequest {

    @NotBlank
    private String destino;
    
    @NotNull @Future
    private LocalDate validoAte;


    public AvisoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }


    public String getDestino() {
        return this.destino;
    }

    public LocalDate getValidoAte() {
        return this.validoAte;
    }
}
