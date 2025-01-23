import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {




    public static void cadatrarCliente(Scanner scanner) throws SQLException {
       System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        String sql = "INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)";

        try(Connection com = DriverManager.getConnection("", "root", "1234");
            PreparedStatement stmt = conn.prepareStatement(sql)
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
}
}