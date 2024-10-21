package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.Serializable;

public class TelaAcao extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador, txtNome, txtDataValidade, txtValorUnitario;
    private static final String ARQUIVO = "acao.txt";

    public TelaAcao() {
        setTitle("Tela de Ação");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel lblTitulo = new JLabel("Tela de Ação", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));

        JLabel lblIdentificador = new JLabel("Identificador:");
        lblIdentificador.setForeground(Color.WHITE);
        txtIdentificador = new JTextField();
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        txtNome = new JTextField();
        JLabel lblDataValidade = new JLabel("Data Validade (aaaa-mm-dd):");
        lblDataValidade.setForeground(Color.WHITE);
        txtDataValidade = new JTextField();
        JLabel lblValorUnitario = new JLabel("Valor Unitário:");
        lblValorUnitario.setForeground(Color.WHITE);
        txtValorUnitario = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnVoltar = new JButton("Voltar");

        Color azul = new Color(30, 144, 255);
        Color branco = Color.WHITE;

        configurarBotao(btnSalvar, azul, branco);
        configurarBotao(btnVoltar, azul, branco);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaMenu mainScreen = new TelaMenu();
                mainScreen.setVisible(true);
                dispose();
            }
        });

        panel.add(Box.createVerticalStrut(50));
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblIdentificador);
        panel.add(txtIdentificador);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblDataValidade);
        panel.add(txtDataValidade);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblValorUnitario);
        panel.add(txtValorUnitario);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnSalvar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVoltar);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        add(panel);
        carregarDados();
    }

    private void configurarBotao(JButton botao, Color bgColor, Color fgColor) {
        botao.setBackground(bgColor);
        botao.setForeground(fgColor);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.PLAIN, 24));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(500, 60));
    }

    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String identificador = txtIdentificador.getText();
            String nome = txtNome.getText();
            String dataValidade = txtDataValidade.getText();
            String valorUnitario = txtValorUnitario.getText();

            writer.write(identificador + ";" + nome + ";" + dataValidade + ";" + valorUnitario);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
            limparCampos();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados.");
        }
    }

    private void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            if ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                txtIdentificador.setText(dados[0]);
                txtNome.setText(dados[1]);
                txtDataValidade.setText(dados[2]);
                txtValorUnitario.setText(dados[3]);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler os dados.");
        }
    }

    private void limparCampos() {
        txtIdentificador.setText("");
        txtNome.setText("");
        txtDataValidade.setText("");
        txtValorUnitario.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAcao();
            }
        });
    }
}
