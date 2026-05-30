package model;

import java.util.ArrayList;
import java.util.List;

public class ClienteVO {
    public int id_cliente;
    public Endereco endereco;
    public String nome_completo;
    public String cpf;
    public String data_nascimento;

    public List<String> emails;
    public List<String> telefones;
    public List<ContaVO> contas;

    public ClienteVO(
        int id_cliente,
        Endereco endereco,
        String nome_completo,
        String cpf,
        String data_nascimento, 
        List<String> emails, 
        List<String> telefones,
        List<ContaVO> contas
    ) {
        this.id_cliente = id_cliente;
        this.endereco = endereco;
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.emails = emails;
        this.telefones = telefones;
        this.contas = contas;
    }
;
    public int getId() { return id_cliente; }
    public Endereco getEndereco() { return this.endereco; }
    public String getNome() { return nome_completo; }
    public String getCpf() { return cpf; }
    public String getData_nascimento() { return data_nascimento; }
    public List<String> getEmails(){ return this.emails; }
    public List<String> getTelefones(){ return this.telefones; }
    public List<ContaVO> getContas(){ return this.contas; }

    @Override
    public String toString(){
        List<String> listaCompactaContas = new ArrayList<>();
        for(ContaVO c : contas) listaCompactaContas.add(c.compactList());
        
        return String.format(
            "\n=== CLIENTE [#%d] ===\n" +
            "Nome: %s | CPF: %s | Nascimento: %s\n" +
            "Telefone(s): %s\n" + 
            "E-mail(s):%s\n" +
            "Endereço:%s\n" +
            "------------------------\n" +
            "Contas:\n%s\n" +
            "========================\n",
            id_cliente, nome_completo, cpf, data_nascimento,
            String.join("\n", telefones),   
            String.join("\n", emails),
            endereco,
            String.join("\n", listaCompactaContas)
        );
    }

    public String compactPrint(){
        return String.format(
            "ID: %d | %s",
            id_cliente, nome_completo
        );
    }
}
