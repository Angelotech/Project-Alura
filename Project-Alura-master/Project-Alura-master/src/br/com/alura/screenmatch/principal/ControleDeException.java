package br.com.alura.screenmatch.principal;

public class ControleDeException extends RuntimeException{

    private String menssagem;

    public ControleDeException (String menssagem){
        this.menssagem = menssagem;
    }

    @Override
    public String getMessage() {
        return this.menssagem;
    }
}
