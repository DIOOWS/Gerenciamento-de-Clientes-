import java.sql.*;
import java.util.Scanner;
import utils.BancoDeDados;
public class LoginClientes {
    BancoDeDados bancoDeDados;
    public static void cadastrarCliente(Scanner scanner) {
       System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        String sql = "INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_clientes?useSSL=false&serverTimezone=UTC", "root", "1234");
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);

            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Erro: Este email já está cadastrado!");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluirCliente(Scanner scanner) throws SQLException {
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

    static int fazerLogin(Scanner scanner){
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
            int clienteId = rs.getInt("id");
            String nomeCliente = rs.getString("nome");
            System.out.println("Login bem-sucedido! Bem-vindo: " + nomeCliente);
            Menu menu = new Menu();
            menu.menuProdutos(clienteId, scanner);
            return clienteId; // Retorna o ID do cliente logado
        }else {
            System.out.println("Email ou senha inválidos. Tente novamente.");
            return -1; // Indica login falhou
            }
        }catch (SQLException e){
            e.printStackTrace();
            return -1; // Indica erro no login
        }
    }
}