import java.sql.*;
import java.util.Scanner;

public class LoginClientes {
    // Variáveis de conexão (fora do método, para uso em todo o código)
    String url = "jdbc:mysql://localhost:3306/login_clientes?useSSL=false&serverTimezone=UTC";
    String USER = "root";
    String PASSWORD = "1234";

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

    static void fazerLogin(Scanner scanner){
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