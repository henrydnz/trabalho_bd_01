package model;

public class Banco {
    private String nome;
    private String cnpj;

    public Banco(String nome, String cnpj){
        this.nome = nome; 
        this.cnpj = cnpj;
    }

    public String getNome(){ return this.nome; }
    public String getCnpj(){ return this.cnpj; }
}
