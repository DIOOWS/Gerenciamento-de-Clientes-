import java.sql.*;
import java.util.Scanner;

public class Produtos extends LoginClientes {


    public static void cadastrarProduto(Scanner scanner) {
       System.out.print("Produto: ");
        String nome_produto = scanner.nextLine();
        System.out.print("Marca: ");
        String marca_produtp = scanner.nextLine();
        System.out.print("Quantidade: ");
        String quantidade_produto = scanner.nextLine();

        String sql = "INSERT INTO cliente (nome_produto, marca, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_clientes?useSSL=false&serverTimezone=UTC", "root", "1234");
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nome_produto);
            stmt.setString(2, marca_produtp);
            stmt.setString(3, quantidade_produto);

            stmt.executeUpdate();
            System.out.println("Prudo " + nome_produto + " cadastrado!");
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Erro: " + nome_produto + " não Cadastrado!");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluirProdutos(Scanner scanner) throws SQLException {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        String sql = "DELETE FROM cliente WHERE nome = ? AND email = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_clientes?useSSL=false&serverTimezone=UTC", "root", "1234");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);

            int ec = stmt.executeUpdate();

              if (ec > 0){
            System.out.println("Cadastro excluido Com Sucesso!");
        }else {
            System.out.println("Email ou senha inválidos. Tente novamente.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    static void listarProdutos(Scanner scanner){
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_clientes", "root", "1234");
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

        if (rs.next()){
            System.out.println("Login bem-sucedido! Bem-vindo, " + rs.getString("nome") + "!");
        }else {
            System.out.println("Email ou senha inválidos. Tente novamente.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
