package cadastroclientes.view;

import cadastroclientes.dao.ClienteDAO;

import javax.swing.*;
import java.awt.*;

public class TelaDeletarClientes extends JFrame {

    private JTextField txtId;
    private JButton btnConfirmarDelecao;

    public TelaDeletarClientes() {
        setTitle("Excluir Cliente");
        setSize(320, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel lblId = new JLabel("Digite o ID a remover:");
        txtId = new JTextField(10);
        btnConfirmarDelecao = new JButton("Deletar");

        add(lblId);
        add(txtId);
        add(btnConfirmarDelecao);

        btnConfirmarDelecao.addActionListener(e -> executarDelecao());
    }

    private void executarDelecao() {
        String textoId = txtId.getText().trim();

        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um ID!");
            return;
        }

        try {
            int id = Integer.parseInt(textoId);

            ClienteDAO dao = new ClienteDAO();
            String resultado = dao.deletarCliente(id);

            JOptionPane.showMessageDialog(this, resultado);
            txtId.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Digite apenas números no campo ID!",
                    "Erro de Formato",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}