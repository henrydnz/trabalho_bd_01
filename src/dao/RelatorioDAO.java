package dao;

import singleton.DatabaseConnection;

import model.ClienteVO;
import model.ContaVO;
import model.TransferenciasVO;
import model.InvestimentosVO;
import model.Endereco;
import model.Agencia;
import model.Banco;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelatorioDAO {
    private static Connection conn = DatabaseConnection.getInstance().getConnection();

    private static ResultSet select(String target, String param, int id){
        String sql = "SELECT * FROM " + target + " WHERE " + param + " = ?";
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }

        return rs;
    }

    public static ContaVO getContaByID(int id_conta) {
        ContaVO conta = null;

        ResultSet rs = select("vw_Resumo_Contas", "id_Conta", id_conta);

        if (rs == null) {
            System.err.println("erro ao pesquisar conta");
            return null;
        }

        try {
            if(rs.next()){
                int id_agencia = rs.getInt("id_Agencia");
                
                Banco banco = new Banco(
                    rs.getString("nome_Banco"),
                    rs.getString("CNPJ_Banco")
                );

                Agencia ag = new Agencia(
                    id_agencia,
                    banco, 
                    rs.getBoolean("is_Virtual"), 
                    null
                );



                ClienteVO titular = new ClienteVO(
                    0, null, 
                    rs.getString("Titular_Conta"), rs.getString("CPF"), "", 
                    null, null
                );

                conta = new ContaVO(
                    rs.getInt("id_Conta"),
                    ag,
                    titular
                );
            } else {
                System.err.println("Nenhuma conta cadastrada com esse ID!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conta;
    }

    public static ClienteVO getClienteByID(int id_cliente) {
        ClienteVO cliente = null;

        ResultSet rsPerfil = select("vw_Perfil_Cliente", "id_cliente", id_cliente);
        ResultSet rsEmails = select("vw_Emails_Clientes", "id_cliente", id_cliente);
        ResultSet rsTelefones = select("vw_Telefones_Clientes", "id_cliente", id_cliente);

        if(rsPerfil == null) {
            System.err.println("erro ao pesquisar cliente");
            return null;
        }

        try {
            if (rsPerfil.next()) {
                Endereco endereco = new Endereco(
                    rsPerfil.getString("nome_Rua"),
                    rsPerfil.getString("nome_Bairro"),
                    rsPerfil.getString("nome_Cidade"),
                    rsPerfil.getString("nome_Estado")
                );

                List<String> emails = new ArrayList<>();
                while (rsEmails.next()) { 
                    emails.add(rsEmails.getString("Email_Cliente"));
                }
                
                List<String> telefones = new ArrayList<>();
                while (rsTelefones.next()) {
                    String ddd = rsTelefones.getString("DDD");
                    String numero = rsTelefones.getString("Telefone");

                    telefones.add("(" + ddd + ") " + numero);
                }

                cliente = new ClienteVO(
                    rsPerfil.getInt("id_Cliente"),
                    endereco,
                    rsPerfil.getString("Nome_Completo"),
                    rsPerfil.getString("CPF"),
                    rsPerfil.getString("DataDeNascimento"),
                    emails,
                    telefones
                );
            } else {
                System.err.println("Nenhum cliente cadastrado com esse ID!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return cliente;
    }

    public static List<TransferenciasVO> getTransferenciasByClienteID(int id_cliente){
        List<TransferenciasVO> transferencias = new ArrayList<>();
        
        ResultSet rs = select("vw_Extrato_Conta", "id_cliente", id_cliente);

        if (rs == null) {
            System.err.println("erro ao pesquisar transferencias");
            return null;
        }

        try {
            while(rs.next()){
                transferencias.add(new TransferenciasVO(
                    null, 
                    rs.getString("Tipo_Operacao"),
                    rs.getBoolean("Operacao_Credito"),
                    rs.getString("Descricao_Transacao"),
                    rs.getString("Data_Transacao"),
                    rs.getFloat("Valor"),
                    rs.getString("Nome_Destinatario_Remetente"),
                    rs.getString("CPF_Destinatario_Remetente"),
                    null
                ));
            }
        } catch (Exception e){ 
            e.printStackTrace(); 
        }

        return transferencias;
    }

    public static List<InvestimentosVO> getInvestimentosByClienteID(int id_cliente){
        List<InvestimentosVO> investimentos = new ArrayList<>();

        ResultSet rs = select("vw_Investimentos_Ativos", "id_Cliente", id_cliente);
        
        if (rs == null) {
            System.err.println("erro ao pesquisar investimentos");
            return null;
        }

        try {
            while(rs.next()){
                InvestimentosVO inv = new InvestimentosVO(
                    null, 
                    rs.getString("Categoria_Investimento"),
                    rs.getFloat("Valor_Inicial"),
                    rs.getString("Data_inicial"),
                    rs.getFloat("Valor_Atual"),
                    true,
                    null 
                );
                    
                investimentos.add(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return investimentos;
    }

    public static void main(String[] args) {
        // TODO: melhorar toString views
        // TODO: getTransferenciasByClienteID -> getTransferenciasByContaID
        // TODO: getInvestimentosByClienteID -> getInvestimentosByContaID

        int id_cliente = 1;
        System.out.println("teste cliente id_cliente="+id_cliente);
        System.out.println();
        System.out.println(getClienteByID(id_cliente)+"\n");
        
        int id_conta = 101;
        System.out.println("teste contna id_conta="+id_conta);
        System.out.println();
        System.out.println(getContaByID(id_conta)+"\n");

        System.out.println("teste transferencias id_cliente="+id_cliente);
        System.out.println();
        List<TransferenciasVO> transferencias = getTransferenciasByClienteID(id_cliente);
        for(TransferenciasVO t : transferencias)
            System.out.println(t);
        System.out.println();

        System.out.println("teste investimentos id_cliente="+id_cliente);
        System.out.println();
        List<InvestimentosVO> investimentos = getInvestimentosByClienteID(id_cliente);
        for(InvestimentosVO i : investimentos)
            System.out.println(i);
    }

}