package cadastroclientes.view;

import cadastroclientes.dao.ClienteDAO;
import cadastroclientes.dto.ClienteDto;

import javax.swing.*;
import java.awt.*;

public class TelaEditarClientes extends JFrame {

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JButton btnBuscar;
    private JButton btnSalvar;

    public TelaEditarClientes() {
        setTitle("Editar Cliente");
        setSize(420, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setLayout(new BorderLayout(10, 10));

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        txtId = new JTextField(5);
        btnBuscar = new JButton("Buscar");

        painelBusca.add(new JLabel("ID:"));
        painelBusca.add(txtId);
        painelBusca.add(btnBuscar);

        add(painelBusca, BorderLayout.NORTH);

        JPanel painelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNome = new JTextField(20);
        txtEmail = new JTextField(20);
        txtTelefone = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.EAST;
        painelForm.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        painelForm.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.EAST;
        painelForm.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        painelForm.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0; gbc.anchor = GridBagConstraints.EAST;
        painelForm.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        painelForm.add(txtTelefone, gbc);

        add(painelForm, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        btnSalvar = new JButton("Salvar Alterações");
        painelBotoes.add(btnSalvar);

        add(painelBotoes, BorderLayout.SOUTH);


        alterarEstadoCampos(false);


        btnBuscar.addActionListener(e -> carregarDadosDoCliente());
        btnSalvar.addActionListener(e -> salvarAlteracoes());
    }


    public TelaEditarClientes(int id) {
        this();
        txtId.setText(String.valueOf(id));
        carregarDadosDoCliente();
    }

    private void carregarDadosDoCliente() {
        String textoId = txtId.getText().trim();

        if (textoId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID do cliente!");
            return;
        }

        try {
            int id = Integer.parseInt(textoId);
            ClienteDAO dao = new ClienteDAO();
            ClienteDto cliente = dao.buscarPorId(id);

            if (cliente != null) {
                txtNome.setText(cliente.getNome());
                txtEmail.setText(cliente.getEmail());
                txtTelefone.setText(cliente.getTelefone());
                alterarEstadoCampos(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado!");
                limparCampos();
                alterarEstadoCampos(false);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um ID válido!");
        }
    }

    private void salvarAlteracoes() {
        String textoId = txtId.getText().trim();

        try {
            ClienteDto cliente = new ClienteDto();
            cliente.setId(Integer.parseInt(textoId));
            cliente.setNome(txtNome.getText().trim());
            cliente.setEmail(txtEmail.getText().trim());
            cliente.setTelefone(txtTelefone.getText().trim());

            ClienteDAO dao = new ClienteDAO();
            boolean sucesso = dao.modificarCliente(cliente);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível atualizar o cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao guardar alterações: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarEstadoCampos(boolean ativo) {
        txtNome.setEnabled(ativo);
        txtEmail.setEnabled(ativo);
        txtTelefone.setEnabled(ativo);
        btnSalvar.setEnabled(ativo);
    }

    private void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
    }
}