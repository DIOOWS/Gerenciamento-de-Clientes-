import java.sql.SQLException;
import java.util.Scanner;

public class Menu{
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
    Menu menu = new Menu();
    menu.menuCliente();
    }

    public void menuCliente() throws SQLException {
        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Excluir Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

              switch (opcao) {
                case 1 -> LoginClientes.fazerLogin(scanner);
                case 2 -> LoginClientes.cadastrarCliente(scanner);
                case 3 -> LoginClientes.excluirCliente(scanner);
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
          }while (opcao != 0);
        scanner.close();
    }

    public void menuProdutos(int clienteId, Scanner scanner) throws SQLException {
        int opcaoProdutos;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Excluir Produtos");
            System.out.println("3. Listar Produto Specific");
//                System.out.println("4. Atualizar Produtos");
            System.out.println("4. Exibir Todos os Produtos");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            opcaoProdutos = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoProdutos) {
                case 1 -> Produtos.cadastrarProduto(scanner, clienteId);
                case 2 -> Produtos.excluirProdutos(scanner);
                case 3 -> Produtos.listarProdutos(clienteId, scanner);
                case 4 -> Produtos.exibirTodosOsProdutosDoCliente(clienteId, scanner);
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcaoProdutos != 0);
    }
}


