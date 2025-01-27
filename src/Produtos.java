import java.sql.*;
import java.util.Scanner;

public class Produtos extends LoginClientes {

    // Variáveis de conexão (fora do método, para uso em todo o código)
    String url = "jdbc:mysql://localhost:3306/produtos?useSSL=false&serverTimezone=UTC";
    String USER = "root";
    String PASSWORD = "1234";


    public static void cadastrarProduto(Scanner scanner, int clienteId) {
        System.out.print("Produto: ");
        String nome_produto = scanner.nextLine();
        System.out.print("Marca: ");
        String marca_produto = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        String sql = "INSERT INTO cadastro_produtos (nome_produto, marca_produto, quantidade, cliente_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/produtos?useSSL=false&serverTimezone=UTC",
                "root",
                "1234");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome_produto);
            stmt.setString(2, marca_produto);
            stmt.setInt(3, quantidade);
            stmt.setInt(4, clienteId);

            stmt.executeUpdate();
            System.out.println("Produto " + nome_produto + " cadastrado com sucesso!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Erro: Produto não cadastrado. Detalhes: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluirProdutos(Scanner scanner) throws SQLException {
        System.out.print("Digite o nome do Produto: ");
        String nome_produto = scanner.nextLine();
        String sql = "DELETE FROM cadastro_produtos WHERE nome_produto = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtos?useSSL=false&serverTimezone=UTC", "root", "1234");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome_produto);
            int ec = stmt.executeUpdate();

            if (ec > 0) {
                System.out.println("Produdo excluido com sucesso!");
            } else {
                System.out.println("Produto invalido");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public static void listarProdutos(int clienteId, Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nome_produto = scanner.nextLine();
        String sql = "SELECT * FROM cadastro_produtos WHERE cliente_id = ? AND LOWER(TRIM(nome_produto)) = LOWER(TRIM(?))";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/produtos", "root", "1234");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId); // Filtra pelo cliente logado
            stmt.setString(2, nome_produto); // Filtra o prduto selecionado

            ResultSet rs = stmt.executeQuery();

            System.out.println("Produtos cadastrados:");
            while (rs.next()) {
                System.out.println("Produto: " + rs.getString("nome_produto") +
                        ", Marca: " + rs.getString("marca_produto") +
                        ", Quantidade: " + rs.getInt("quantidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exibirTodosOsProdutosDoCliente(int clienteId, Scanner scanner) {
    String sql = "SELECT c.nome, p.nome_produto, p.marca_produto, p.quantidade " +
                 "FROM produtos.cadastro_produtos p " +
                 "JOIN login_clientes.cliente c ON p.cliente_id = c.id " +
                 "WHERE p.cliente_id = ?";

    try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/produtos?useSSL=false&serverTimezone=UTC",
            "root", "1234");
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, clienteId); // Define o cliente_id como parâmetro

        ResultSet rs = stmt.executeQuery();

        // Verifica se encontrou algum cliente
        if (rs.next()) {
            String nomeCliente = rs.getString("nome"); // Nome do cliente

            System.out.println("Sua Lista Completa: " + nomeCliente);
            boolean encontrouProdutos = false;

            do {
                String nomeProduto = rs.getString("nome_produto");
                String marcaProduto = rs.getString("marca_produto");
                int quantidade = rs.getInt("quantidade");

                System.out.println("Produto: " + nomeProduto + ", Marca: " + marcaProduto + ", Quantidade: " + quantidade);
                encontrouProdutos = true;
            } while (rs.next()); // Continua enquanto houver mais produtos

            if (!encontrouProdutos) {
                System.out.println("Nenhum produto encontrado para este cliente.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}


