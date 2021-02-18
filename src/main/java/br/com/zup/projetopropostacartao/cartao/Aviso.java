package br.com.zup.projetopropostacartao.cartao;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "avisosCartao")
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime validoAte;
    @NotBlank
    private String destino;
    @ManyToOne
    private Cartao cartao;

    public Aviso(@NotNull LocalDateTime validoAte, @NotBlank String destino, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public String toString() {
        return "Avisos{" +
                "id=" + id +
                ", validoAte=" + validoAte +
                ", destino='" + destino + '\'' +
                ", cartao=" + cartao +
                '}';
    }
}
