package model;

public class Endereco {
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String rua, String bairro, String cidade, String estado){
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua(){ return this.rua; }
    public String getBairro(){ return this.bairro; }
    public String getCidade(){ return this.cidade; }
    public String getEstado(){ return this.estado; }

    @Override
    public String toString(){ return String.format("%s, %s - %s/%s", rua, bairro, cidade, estado); }
}
