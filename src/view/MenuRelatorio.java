package view;

import java.util.List;
import java.util.Scanner;

import dao.RelatorioDAO;
import model.ClienteVO;
import model.ContaVO;
import model.InvestimentosVO;
import model.TransferenciasVO;

public class MenuRelatorio {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
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
                case 5:
                    opcaoTodosClientes();
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
        limparTela();

        System.out.println("\n========================================\n");
        System.out.println("             SISTEMA BANCÁRIO\n");
        System.out.println("----------------------------------------");
        System.out.println("1. Ver Transacoes da conta");
        System.out.println("2. Ver investimentos da conta");
        System.out.println("3. Buscar cliente por ID");
        System.out.println("4. Ver informações da conta");
        System.out.println("5. Ver Lista de Clientes");
        System.out.println("0. Sair");
        System.out.println("========================================\n");
    }
    
    private static void opcaoTransacoes() {
        limparTela();
        System.out.println("\n ----- Transacoes DA CONTA -----");

        int idConta = lerInteiro("Digite o número da conta: ");

        List<TransferenciasVO> transacoes = RelatorioDAO.getTransferenciasByContaID(idConta);

        if(transacoes == null) return;

        for (TransferenciasVO item : transacoes) 
            System.out.println(item);

        esperarEnter();
    }
    
    private static void opcaoInvestimentos() {
        limparTela();
        System.out.println("\n--- INVESTIMENTOS DA CONTA ---");

        int idConta = lerInteiro("Digite o ID da conta: ");
        
        List<InvestimentosVO> investimentos = RelatorioDAO.getInvestimentosByContaID(idConta);

        if(investimentos == null) return;

        for (InvestimentosVO item : investimentos) 
            System.out.println(item);

        esperarEnter();
    }
    
    private static void opcaoBuscarCliente() {
        limparTela();
        System.out.println("\n--- BUSCAR CLIENTE ---");
        
        int idCliente = lerInteiro("Digite o ID do Cliente:");
        
        ClienteVO cliente = RelatorioDAO.getClienteByID(idCliente);

        if(cliente == null) return;

        System.out.println(cliente);

        esperarEnter();
    }
    
    private static void opcaoInfoConta() {
        limparTela();
        int idConta = lerInteiro("Digite o ID da Conta: ");
        
        System.out.println("\n--- BUSCAR CONTA ---");
        
        ContaVO conta = RelatorioDAO.getContaByID(idConta);

        if(conta == null) return;

        System.out.println(conta);

        esperarEnter();
    }

     private static void opcaoTodosClientes() {
        limparTela();
        System.out.println("\n--- LISTA DE CLIENTES ---");

        List<ClienteVO> clientes = RelatorioDAO.getClientes();

        if(clientes == null) return;

        for (ClienteVO item : clientes) 
            System.out.println(item.compactPrint());

        esperarEnter();
    }
    
    
    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Digite um número válido!");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    // private static String lerString(String mensagem) {
    //     System.out.print(mensagem);
    //     String valor = scanner.nextLine();
        
    //     while (valor.trim().isEmpty()) {
    //         System.out.println("Nome não pode ser vazio!");
    //         valor = scanner.nextLine();
    //     }

    //     return valor.trim();
    // }

    private static void clear_screen(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static void esperarEnter() {
    System.out.print("\nPressione ENTER para continuar...");
    scanner.nextLine();
}

    private static void limparTela() {
    try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception e) {
        System.out.println("Erro ao limpar tela: " + e.getMessage());
    }
}
}