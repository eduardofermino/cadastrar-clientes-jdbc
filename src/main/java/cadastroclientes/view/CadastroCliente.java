package cadastroclientes.view;

import cadastroclientes.dao.ClienteDAO;
import cadastroclientes.dto.ClienteDto;

import javax.swing.*;

public class CadastroCliente extends JFrame {
    private JPanel panel1;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JButton inserirButton;
    private JButton btnListar;
    private JButton btnAbrirDeletar;
    private JButton btnBuscarId;
    private JButton btnEditar;

    public CadastroCliente() {
        setContentPane(panel1);
        setTitle("Cadastro de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        inserirButton.addActionListener(e -> salvarCliente());
        btnAbrirDeletar.addActionListener(e -> {
            TelaDeletarClientes telaDeletar = new TelaDeletarClientes();
            telaDeletar.setVisible(true);
        });

        btnListar.addActionListener(e -> abrirTelaTabela());
        btnBuscarId.addActionListener(e -> abrirTelaBuscarClientes());
        btnEditar.addActionListener(e -> abrirTelaEditar());
    }


    private void salvarCliente() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o nome do cliente!");
            return;
        }

        ClienteDto c = new ClienteDto();
        c.setNome(txtNome.getText());
        c.setEmail(txtEmail.getText());
        c.setTelefone(txtTelefone.getText());

        ClienteDAO dao = new ClienteDAO();
        dao.inserir(c);

        JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
        limparCampos();
    }


    private void abrirTelaTabela() {
        TelaTabelaClientes tela = new TelaTabelaClientes();
        tela.setVisible(true);
    }

    private void abrirTelaEditar() {
        TelaEditarClientes tela = new TelaEditarClientes();
        tela.setVisible(true);
    }

    private void abrirTelaBuscarClientes() {
        TelaBuscarClientes tela = new TelaBuscarClientes();
        tela.setVisible(true);
    }

    private void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroCliente().setVisible(true);
        });
    }
}