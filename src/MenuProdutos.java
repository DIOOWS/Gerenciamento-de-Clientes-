import java.sql.SQLException;
import java.util.Scanner;

public class MenuProdutos extends Produtos{
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        //Login do cliente
        int clienteId = LoginClientes.fazerLogin(scanner);

        // Chamando o menu principal com o clienteId
         menuCadstroProdutos(clienteId, scanner);
         scanner.close();
    }
    public static void menuCadstroProdutos(int clienteId, Scanner scanner) throws SQLException {

            int opcaoProdutos;
            do {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Cadastrar Produto");
                System.out.println("2. Excluir Produtos");
                System.out.println("3. Listar Produto Especifico");
//                System.out.println("4. Atualizar Produtos");
                System.out.println("5. Exibir Todos os Produtos");
                System.out.println("0. Sair");

                System.out.print("Escolha uma opção: ");
                opcaoProdutos = scanner.nextInt();
                scanner.nextLine();

                switch (opcaoProdutos) {
                    case 1 -> Produtos.cadastrarProduto(scanner, clienteId);
                    case 2 -> Produtos.excluirProdutos(scanner);
                    case 3 -> Produtos.listarProdutos(clienteId, scanner);
                    case 4 -> excluirCliente(scanner);
                    case 5 -> Produtos.exibirTodosOsProdutosDoCliente(clienteId, scanner);
                    case 0 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } while (opcaoProdutos != 0);
        }
}



