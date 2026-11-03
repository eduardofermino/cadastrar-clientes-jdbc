package cadastroclientes.dao;
import cadastroclientes.conexao_db.Conexao;
import cadastroclientes.dto.ClienteDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet set = null;
    ClienteDto clienteDto;

    public void inserir(ClienteDto cliente){
        String query = "insert into clientes(nome, email, telefone) values (?,?,?);";
        con = new Conexao().getConnection();

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getTelefone());
            pst.execute();
            System.out.println("Inserido.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public List<ClienteDto> listarClientes(){
        String query = "SELECT * FROM clientes;";
        con = new Conexao().getConnection();
        List<ClienteDto> clientes = new ArrayList<>();

        try {
            pst = con.prepareStatement(query);
            set = pst.executeQuery();
            while(set.next()){
                clienteDto = new ClienteDto(set.getInt("id"), set.getString("nome"), set.getString("email"), set.getString("telefone"));
                clientes.add(clienteDto);
            }
        } catch (SQLException e){
            System.out.println("Erro ao listar clientes. " + e);
        }

        return clientes;
    }

    public String deletarCliente(int id) {
        String query = "DELETE FROM clientes WHERE id = ?";
        con = new Conexao().getConnection();

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, id);

            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Cliente deletado com sucesso!";
            } else {
                return "Nenhum cliente encontrado com esse ID.";
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e);
            return "Erro ao deletar cliente.";
        }
    }

    public ClienteDto buscarPorId(int id){
        String query = "SELECT * FROM clientes WHERE id = ?";
        con = new Conexao().getConnection();
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            set = pst.executeQuery();

            if (set.next()) {
                return new ClienteDto(
                        set.getInt("id"),
                        set.getString("nome"),
                        set.getString("email"),
                        set.getString("telefone")
                );
            }

        } catch (SQLException e){
            System.out.println("Erro ao buscar cliente. " + e);
            return null;
        }

        return null;


    }

    public boolean modificarCliente(ClienteDto cliente){
        String query = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        con = new Conexao().getConnection();

        try{

            pst = con.prepareStatement(query);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getTelefone());
            pst.setInt(4, cliente.getId());
            int linhasAfetadas = pst.executeUpdate();
            return linhasAfetadas > 0;

        } catch(SQLException e){
            System.out.println("Erro ao atualizar dados. " + e);
            return false;
        }
    }


}
