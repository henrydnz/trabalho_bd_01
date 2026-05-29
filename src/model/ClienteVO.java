package model;

import java.util.List;

public class ClienteVO {
    public int id_cliente;
    public Endereco endereco;
    public String nome;
    public String sobrenome;
    public String cpf;
    public String data_nascimento;

    public List<String> emails;
    public List<String> telefones;

    public ClienteVO(
        int id_cliente,
        Endereco endereco,
        String nome,
        String sobrenome,
        String cpf,
        String data_nascimento, 
        List<String> emails, 
        List<String> telefones
    ) {
        this.id_cliente = id_cliente;
        this.endereco = endereco;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.emails = emails;
        this.telefones = telefones;
    }
;
    public int getId() { return id_cliente; }
    public Endereco getEndereco() { return this.endereco; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getCpf() { return cpf; }
    public String getData_nascimento() { return data_nascimento; }
    public List<String> getEmails(){ return this.emails; }
    public List<String> getTelefones(){ return this.telefones; }

    @Override
    public String toString(){
        return String.format(
            "=== CLIENTE [#%d] ===\n" +
            "Nome: %s %s | CPF: %s | Nascimento: %s\n" +
            "Contatos: %s | E-mails: %s\n" +
            "Endereço: %s\n" +
            "========================",
            id_cliente, nome, sobrenome, cpf, data_nascimento,
            ((emails != null && !emails.isEmpty()) ? String.join(", ", emails) : "Nenhum e-mail"),
            ((telefones != null && !telefones.isEmpty()) ? String.join(", ", telefones) : "Nenhum telefone"),   
            ((endereco != null) ? endereco.toString() : "Endereço não cadastrado")
        );
    }
}
