package model;

public class Agencia {
    int id_agencia;
    Banco banco;
    boolean is_virtual;
    Endereco endereco;

    public Agencia(
        int id_agencia, 
        Banco banco,
        boolean is_virtual,
        Endereco endereco
    ) {
        this.id_agencia = id_agencia;
        this.banco = banco;
        this.is_virtual = is_virtual;
        this.endereco = this.is_virtual ? endereco : null;
    }

    public int getId(){ return this.id_agencia; }
    public Banco getBanco(){ return this.banco; }
}
