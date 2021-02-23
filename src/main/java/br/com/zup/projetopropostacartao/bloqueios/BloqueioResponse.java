package br.com.zup.projetopropostacartao.bloqueios;

import br.com.zup.projetopropostacartao.cartao.StatusBloqueio;

public class BloqueioResponse {

    private StatusBloqueio status;

    @Deprecated
    public BloqueioResponse(){}

    public BloqueioResponse(StatusBloqueio status) {
        this.status = status;
    }

    public StatusBloqueio getStatus() {
        return this.status;
    }

    public boolean isBloqueado() {
        if (this.status.equals(StatusBloqueio.BLOQUEADO)) {
            return true;
        }
        return false;
    }
}
