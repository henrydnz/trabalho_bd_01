package dao;

import singleton.DatabaseConnection;

import model.ClienteVO;
import model.ContaVO;
import model.TransferenciasVO;
import model.InvestimentosVO;
import model.Endereco;

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
            System.err.println("erro ao executar query.");
            System.err.println("sql string: SELECT * FROM " + target + " WHERE " + param + " = " + id);

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
                conta = new ContaVO(
                    rs.getInt("id_Conta"),
                    rs.getInt("id_Agencia"),
                    rs.getString("nome_Banco"),
                    rs.getString("CNPJ_Banco"),
                    rs.getString("Titular_Conta"), 
                    rs.getString("CPF"),
                    rs.getFloat("Saldo")
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

        ResultSet rsPerfil = select("vw_Perfil_Cliente", "id_Cliente", id_cliente);
        ResultSet rsEmails = select("vw_Emails_Clientes", "id_Cliente", id_cliente);
        ResultSet rsTelefones = select("vw_Telefones_Clientes", "id_Cliente", id_cliente);
        ResultSet rsContas = select("vw_Resumo_Contas", "id_Cliente", id_cliente);

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
            
                List<ContaVO> contas = new ArrayList<>();
                while(rsContas.next()){
                    contas.add(new ContaVO(
                        rsContas.getInt("id_Conta"),
                        rsContas.getInt("id_Agencia"),
                        rsContas.getString("nome_Banco"), 
                        rsContas.getString("CNPJ_banco"),
                        rsContas.getString("Titular_Conta"), 
                        rsContas.getString("CPF"),
                        rsContas.getFloat("Saldo")
                    ));
                }

                cliente = new ClienteVO(
                    rsPerfil.getInt("id_Cliente"),
                    endereco,
                    rsPerfil.getString("Nome_Completo"),
                    rsPerfil.getString("CPF"),
                    rsPerfil.getString("DataDeNascimento"),
                    emails,
                    telefones,
                    contas
                );

            } else {
                System.err.println("Nenhum cliente cadastrado com esse ID!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return cliente;
    }

    public static List<TransferenciasVO> getTransferenciasByContaID(int id_conta){
        List<TransferenciasVO> transferencias = new ArrayList<>();
        
        ResultSet rs = select("vw_Extrato_Conta", "id_Conta", id_conta);

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
                    rs.getString("CPF_Destinatario_Remetente")
                ));
            }
        } catch (Exception e){ 
            e.printStackTrace(); 
        }

        return transferencias;
    }

    public static List<InvestimentosVO> getInvestimentosByContaID(int id_conta){
        ContaVO conta = getContaByID(id_conta);
        if (conta == null) return null;
        
        List<InvestimentosVO> investimentos = new ArrayList<>();
        ResultSet rsInv = select("vw_Investimentos_Ativos", "id_Conta", id_conta);
        
        if (rsInv == null) {
            System.err.println("erro ao pesquisar investimentos");
            return null;
        }

        try {
            while(rsInv.next()){
                InvestimentosVO inv = new InvestimentosVO(
                    conta, 
                    rsInv.getString("Categoria_Investimento"),
                    rsInv.getFloat("Valor_Inicial"),
                    rsInv.getString("Data_inicial"),
                    rsInv.getFloat("Valor_Atual")
                );
                    
                investimentos.add(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return investimentos;
    }

    public static void main(String[] args) {
        int id_cliente = 1;
        System.out.println("teste cliente (id_cliente="+id_cliente+")");
        System.out.println(getClienteByID(id_cliente));
        
        int id_conta = 101;
        System.out.println("teste conta (id_conta=" + id_conta + "):");
        System.out.println(getContaByID(id_conta));

        System.out.println("teste transferencias (id_conta=" + id_conta + "):");
        List<TransferenciasVO> transferencias = getTransferenciasByContaID(id_conta);
        for(TransferenciasVO t : transferencias)
            System.out.println(t);

        System.out.println("\nteste investimentos (id_conta=" + id_conta + "):");
        List<InvestimentosVO> investimentos = getInvestimentosByContaID(id_conta);
        for(InvestimentosVO i : investimentos)
            System.out.println(i);
    }
}
