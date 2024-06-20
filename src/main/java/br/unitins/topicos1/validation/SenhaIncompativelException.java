package br.unitins.topicos1.validation;

public class SenhaIncompativelException extends RuntimeException {
    public SenhaIncompativelException(String message) {
        super(message);
    }
}