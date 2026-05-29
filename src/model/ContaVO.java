package model;

public class ContaVO {
    private int id_conta;
    private Agencia agencia;
    private ClienteVO cliente;

    private float saldo;

    public ContaVO(
        int id_conta, 
        Agencia agencia, 
        ClienteVO cliente
    ) {
        this.id_conta = id_conta;
        this.agencia = agencia;
        this.cliente = cliente;
    }

    public int getId(){ return this.id_conta; }
    public Agencia getAgencia(){ return this.agencia; }
    public ClienteVO getCliente(){ return this.cliente; }
    public float getSaldo(){ return this.saldo; }

    @Override
    public String toString(){
        
        String titular = (cliente == null) ? 
            "Cliente não informado" :
            cliente.getNome() + " (CPF: " + cliente.getCpf() + ")";
        
        String ag = (agencia != null) ? "Ag. " + agencia.getId() : "Ag. Desconhecida";
        String bc = (agencia != null) ? agencia.getBanco().getNome() : "Banco Desconhecido";

        return String.format("Conta: %05d | %s - %s | Titular: %s | Saldo Disponível: %s",
            id_conta, bc, ag, titular, String.format("R$ %.2f", saldo)
        );
    }
}


