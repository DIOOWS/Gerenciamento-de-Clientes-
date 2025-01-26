import java.sql.SQLException;
import java.util.Scanner;

public class MenuProdutos extends Produtos{
    public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    int opcaoProdutos;

    do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Atualizar Produtos");
            System.out.println("3. Excluir Produtos");
            System.out.println("3. Listar Produto Especifico");
            System.out.println("3. Exibir Todos os Produtos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoProdutos = scanner.nextInt();
            scanner.nextLine();

              switch (opcaoProdutos) {
                case 1 -> fazerLogin(scanner);
                case 2 -> cadastrarCliente(scanner);
                case 3 -> excluirCliente(scanner);
                case 4 -> excluirCliente(scanner);
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
          }while (opcaoProdutos != 0);
      scanner.close();
    }

}



