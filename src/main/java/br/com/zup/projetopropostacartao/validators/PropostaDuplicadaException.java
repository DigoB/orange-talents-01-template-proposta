package br.com.zup.projetopropostacartao.validators;

public class PropostaDuplicadaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PropostaDuplicadaException (String msg) {
        super(msg);
    }
}