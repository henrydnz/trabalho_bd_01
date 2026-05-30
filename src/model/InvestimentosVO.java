package model;

public class InvestimentosVO {
    ContaVO conta;
    String tipo;
    float valor_inicial;
    String data_inicial;
    float valor_atual;

    public InvestimentosVO(
        ContaVO conta,
        String tipo,
        float valor_inicial, 
        String data_inicial,
        float valor_atual
    ) {
        this.conta = conta;
        this.tipo = tipo;
        this.valor_inicial = valor_inicial;
        this.data_inicial = data_inicial;
        this.valor_atual = valor_atual;
    }

    public ContaVO getConta(){ return this.conta; }
    public String getTipo(){ return this.tipo; }
    public float getValorInicial(){ return this.valor_inicial; }
    public String getDataInicial(){ return this.data_inicial; }
    public float getValorAtual(){ return this.valor_atual; }


    @Override
    public String toString(){
        return String.format("%s - Desde %s | R$ %.2f -> R$ %.2f (Rendimento atual: %+.2f%s)", 
            this.tipo, data_inicial, valor_inicial, valor_atual,
            (valor_inicial > 0 ? ((valor_atual - valor_inicial) / valor_inicial) * 100 : 0), "%"
        );
    }
}
