package cadastroclientes.conexao_db;

import java.sql.*;

public class Conexao {

    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro_clientes";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection(){

        Connection con = null;

        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexao criada.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e);
        }

        return con;
    }
}
