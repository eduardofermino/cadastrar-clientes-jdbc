package cadastroclientes.view;

import cadastroclientes.dao.ClienteDAO;
import cadastroclientes.dto.ClienteDto;

import javax.swing.*;
import java.awt.*;

public class TelaBuscarClientes extends JFrame {

    private JTextField txtId;
    private JButton btnBuscarCliente;

    public TelaBuscarClientes() {
        setTitle("Buscar Cliente");
        setSize(320, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lblId = new JLabel("Digite o ID do cliente:");
        txtId = new JTextField(10);
        btnBuscarCliente = new JButton("Buscar");

        add(lblId);
        add(txtId);
        add(btnBuscarCliente);

        btnBuscarCliente.addActionListener(e -> buscarPorId());
    }

    public void buscarPorId() {
        String textoId = txtId.getText().trim();

        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um ID!");
            return;
        }

        try {
            int id = Integer.parseInt(textoId);

            ClienteDAO dao = new ClienteDAO();
            ClienteDto clienteBuscado = dao.buscarPorId(id);

            // Verifica se o cliente foi encontrado no banco
            if (clienteBuscado != null) {
                String informacoes = "Cliente Encontrado:\n\n" +
                        "ID: " + clienteBuscado.getId() + "\n" +
                        "Nome: " + clienteBuscado.getNome() + "\n" +
                        "E-mail: " + clienteBuscado.getEmail() + "\n" +
                        "Telefone: " + clienteBuscado.getTelefone();

                JOptionPane.showMessageDialog(this, informacoes, "Busca de Cliente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com o ID " + id, "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite apenas números no campo ID!", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }


}