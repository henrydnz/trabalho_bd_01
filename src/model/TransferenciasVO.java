package model;

public class TransferenciasVO {
    ContaVO conta;

    String tipo_transferencia;
    boolean is_credito;
    
    String complemento;
    String data;
    
    float valor;

    String nome_complementar;
    String cpf_complementar;
    Agencia agencia_complementar;

    public TransferenciasVO(
        ContaVO conta, 
        String tipo_transferencia,
        boolean is_credito,
        String complemento,
        String data,
        float valor,
        String nome_complementar,
        String cpf_complementar,
        Agencia agencia_complementar
    ) {
        this.conta = conta;
        this.tipo_transferencia = tipo_transferencia;
        this.is_credito = is_credito;
        this.complemento = complemento;
        this.data = data;
        this.valor = valor;
        this.nome_complementar = nome_complementar;
        this.cpf_complementar = cpf_complementar;
        this.agencia_complementar = agencia_complementar;
    }

    public ContaVO getConta(){ return this.conta; }
    public String getTipoTransferencia(){ return this.tipo_transferencia; }
    public boolean getIsCredito(){ return this.is_credito; }
    public String getComplemento(){ return this.complemento; }
    public String getData(){ return this.data; }
    public float getValor(){ return this.valor; }
    public String getNomeComplementar(){ return this.nome_complementar; }
    public String getCpfComplementar(){ return this.cpf_complementar; }

    @Override
    public String toString(){
        return String.format("[%s] %s | %s %s | %s: %s (CPF: %s)",
            this.data,
            this.tipo_transferencia,
            (this.is_credito ? "+" : "-"),
            String.format("R$ %.2f", Math.abs(this.valor)),
            (this.is_credito ? "Destino" : "Origem"),
            this.nome_complementar, 
            this.cpf_complementar
        );
    }
}
