import java.sql.SQLException;
import java.util.Scanner;

public class Menu extends LoginClientes{
    public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    int opcao;

    do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Fazer Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

              switch (opcao) {
                case 1 -> cadastrarCliente(scanner);
                case 2 -> fazerLogin(scanner);
                case 3 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
          }while (opcao != 3);
      scanner.close();
    }

}

