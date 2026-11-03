package cadastroclientes.view;

import cadastroclientes.dao.ClienteDAO;
import cadastroclientes.dto.ClienteDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaTabelaClientes extends JFrame {

    private JTable tabela;
    private DefaultTableModel model;
    private JButton btnAtualizarLista;

    public TelaTabelaClientes() {
        setTitle("Lista de Clientes");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colunas = {"ID", "Nome", "E-mail", "Telefone"};
        model = new DefaultTableModel(colunas, 0);
        tabela = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel();

        btnAtualizarLista = new JButton("Atualizar Lista");
        btnAtualizarLista.addActionListener(e -> carregarTabela());

        painelBotoes.add(btnAtualizarLista);


        add(painelBotoes, BorderLayout.SOUTH);

        carregarTabela();
    }

    private void carregarTabela() {
        ClienteDAO dao = new ClienteDAO();
        List<ClienteDto> lista = dao.listarClientes();

        model.setRowCount(0);

        for (ClienteDto c : lista) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getEmail(),
                    c.getTelefone()
            });
        }
    }

}