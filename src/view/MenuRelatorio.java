package view;

// Trocar pelos Model
import dao.RelatorioDAO;
import model.TransacoesVO;
import model.InvestimentosVO;
import model.ClienteVO;
import model.ContaInfoVO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MenuRelatorio {
    
    private static RelatorioDAO relatorioDAO = new RelatorioDAO();
    
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat formatadorDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    public static void main(String[] args) {
        System.out.println("========================================\n");
        System.out.println("             SISTEMA BANCÁRIO\n");
        System.out.println("========================================\n");
        
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    opcaoTransacoes();
                    break;
                case 2:
                    opcaoInvestimentos();
                    break;
                case 3:
                    opcaoBuscarCliente();
                    break;
                case 4:
                    opcaoInfoConta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida.\n");
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("----------------------------------------");
        System.out.println("1. Ver Transacoes da conta");
        System.out.println("2. Ver investimentos do cliente");
        System.out.println("3. Buscar cliente por nome");
        System.out.println("4. Ver informações da conta");
        System.out.println("0. Sair");
        System.out.println("----------------------------------------");
    }
    
    private static void opcaoTransacoes() {
        
        int idConta = lerInteiro("Digite o número da conta: ");
        
        System.out.println("\n ----- Transacoes DA CONTA -----");

        List<TransacoesVO> transacoes = relatorioDAO.buscarTransacoesPorConta(idConta);
        
        for (TransacoesVO item : transacoes) {
            System.out.println(item);
        }

    }
    
    private static void opcaoInvestimentos() {
        int idCliente = lerInteiro("Digite o ID do cliente: ");
        
        System.out.println("\n--- INVESTIMENTOS DO CLIENTE ---");
        
        List<InvestimentosVO> investimentos = relatorioDAO.buscarInvestimentosPorConta(idCliente);
        
        for (InvestimentosVO item : investimentos) {
            System.out.println(item);
        }
    }
    
    private static void opcaoBuscarCliente() {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        
        String nome = lerString("Digite o nome do Cliente:");
        
        ClienteVO cliente = relatorioDAO.buscarClientePorNome(nome);

        System.out.println(cliente);
    }
    
    private static void opcaoInfoConta() {
        int idConta = lerInteiro("Digite o ID da Conta: ");
        
        System.out.println("\n--- BUSCAR CONTA ---");
        
        ContaVO conta = relatorioDAO.buscarContaPorId(idConta);

        System.out.println(conta);
    }
    
    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Digite um número válido!");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer
        return valor;
    }

    private static String lerString(String mensagem) {
    System.out.print(mensagem);
    String valor = scanner.nextLine();
    
    while (valor.trim().isEmpty()) {
        System.out.println("Nome não pode ser vazio!");
        valor = scanner.nextLine();
    }
    
    return valor.trim();
}
}