package model;

public class InvestimentosVO {
    ContaVO conta;
    String tipo;
    float valor_inicial;
    String data_inicial;
    float valor_atual;
    boolean is_ativo;
    String data_encerramento;

    public InvestimentosVO(
        ContaVO conta,
        String tipo,
        float valor_inicial, 
        String data_inicial,
        float valor_atual,
        boolean is_ativo,
        String data_encerramento
    ) {
        this.conta = conta;
        this.tipo = tipo;
        this.valor_inicial = valor_inicial;
        this.valor_atual = valor_atual;
        this.is_ativo = is_ativo;
        this.data_encerramento = this.is_ativo ? null : data_encerramento;
    }

    public ContaVO getConta(){ return this.conta; }
    public String getTipo(){ return this.tipo; }
    public float getValorInicial(){ return this.valor_inicial; }
    public String getDataInicial(){ return this.data_inicial; }
    public float getValorAtual(){ return this.valor_atual; }
    public boolean getAtivo(){ return this.is_ativo; }
    public String getDataEncerramento(){ return this.data_encerramento; }

    @Override
    public String toString(){
        return String.format("%s - %s | %s -> %s (%s)", 
            this.tipo,
            String.format("Desde %s", data_inicial),
            String.format("R$ %.2f", valor_inicial),
            String.format("R$ %.2f", valor_atual),
            String.format("%+.2f" + "%", (valor_inicial > 0 ? ((valor_atual - valor_inicial) / valor_inicial) * 100 : 0))
        );
    }
}
