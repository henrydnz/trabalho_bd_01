package model;

public class ContaVO {
    private int id_conta;

    // private Agencia;
    private int id_agencia;

    //private Banco banco;
    private String nome_banco;
    private String cnpj_banco;

    // private ClienteVO titular;
    private String nome_titular;
    private String cpf_titular;

    private float saldo;

    public ContaVO(
        int id_conta, 
        int id_agencia, 
        String nome_banco,
        String cnpj_banco,
        String nome_titular,
        String cpf_titular,
        float saldo
    ) {
        this.id_conta = id_conta;
        this.id_agencia = id_agencia;
        this.nome_banco = nome_banco;
        this.cnpj_banco = cnpj_banco;
        this.nome_titular = nome_titular;
        this.cpf_titular = cpf_titular;
        this.saldo = saldo;
    }

    public int getId(){ return this.id_conta; }
    public int getIdAgencia(){ return this.id_agencia; }
    public String getNomeBanco(){ return this.nome_banco; }
    public String getCnpjBanco(){ return this.cnpj_banco; }
    public String getNomeTitular(){ return this.nome_titular; }
    public String getCpfTitular(){ return this.cpf_titular; }
    public float getSaldo(){ return this.saldo; }

    @Override
    public String toString(){
        return String.format(
            "\nID: %05d | %s (CNPJ: %s) - Agência: %d\n" + 
            "Titular: %s (CPF: %s) | Saldo Disponível: R$ %.2f\n",
            id_conta, nome_banco, cnpj_banco, id_agencia, 
            nome_titular, cpf_titular, saldo
        );
    }

    public String compactList(){
        return String.format(
            "ID: %05d | %s (CNPJ: %s) - Agência: %d | Saldo: R$ %.2f",
            id_conta, nome_banco, cnpj_banco, id_agencia, saldo
        );
    }
}


