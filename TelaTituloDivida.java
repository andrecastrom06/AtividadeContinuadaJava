package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TelaTituloDivida extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    private JTextField txtId, txtNome, txtDataValidade, txtTaxaJuros;
    private MediatorTituloDivida mediator = MediatorTituloDivida.getInstanciaSingleton();

    public TelaTituloDivida() {
        setTitle("Gestão de Títulos de Dívida");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel lblTitulo = new JLabel("Gestão de Títulos de Dívida", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));

        JLabel lblId = new JLabel("Identificador:");
        lblId.setForeground(Color.WHITE);
        txtId = new JTextField();

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        txtNome = new JTextField();

        JLabel lblDataValidade = new JLabel("Data Validade (AAAA-MM-DD):");
        lblDataValidade.setForeground(Color.WHITE);
        txtDataValidade = new JTextField();

        JLabel lblTaxaJuros = new JLabel("Taxa de Juros:");
        lblTaxaJuros.setForeground(Color.WHITE);
        txtTaxaJuros = new JTextField();

        JButton btnIncluir = new JButton("Incluir");
        JButton btnAlterar = new JButton("Alterar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnVoltar = new JButton("Voltar");

        Color azul = new Color(30, 144, 255);
        Color branco = Color.WHITE;

        configurarBotao(btnIncluir, azul, branco);
        configurarBotao(btnAlterar, azul, branco);
        configurarBotao(btnExcluir, azul, branco);
        configurarBotao(btnBuscar, azul, branco);
        configurarBotao(btnVoltar, azul, branco);

        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirTitulo();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarTitulo();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTitulo();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTitulo();
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
        panel.add(lblId);
        panel.add(txtId);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblDataValidade);
        panel.add(txtDataValidade);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblTaxaJuros);
        panel.add(txtTaxaJuros);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnIncluir);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnAlterar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnExcluir);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnBuscar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnVoltar);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        add(panel);
    }

    private void configurarBotao(JButton botao, Color bgColor, Color fgColor) {
        botao.setBackground(bgColor);
        botao.setForeground(fgColor);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.PLAIN, 24));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(500, 60));
    }

    private void incluirTitulo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText());
            double taxaJuros = Double.parseDouble(txtTaxaJuros.getText());

            TituloDivida titulo = new TituloDivida(id, nome, dataValidade, taxaJuros);
            String resultado = mediator.incluir(titulo);

            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Título incluído com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, resultado);
            }
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Verifique os valores numéricos e o formato da data.");
        }
    }

    private void alterarTitulo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText());
            double taxaJuros = Double.parseDouble(txtTaxaJuros.getText());

            TituloDivida titulo = new TituloDivida(id, nome, dataValidade, taxaJuros);
            String resultado = mediator.alterar(titulo);

            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Título alterado com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, resultado);
            }
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Verifique os valores numéricos e o formato da data.");
        }
    }

    private void excluirTitulo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String resultado = mediator.excluir(id);

            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Título excluído com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, resultado);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: O ID deve ser numérico.");
        }
    }

    private void buscarTitulo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            TituloDivida titulo = mediator.buscar(id);

            if (titulo != null) {
                txtNome.setText(titulo.getNome());
                txtDataValidade.setText(titulo.getDataDeValidade().toString());
                txtTaxaJuros.setText(String.valueOf(titulo.getTaxaJuros()));
                JOptionPane.showMessageDialog(this, "Título encontrado!");
            } else {
                JOptionPane.showMessageDialog(this, "Título não encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: O ID deve ser numérico.");
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtDataValidade.setText("");
        txtTaxaJuros.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaTituloDivida().setVisible(true);
        });
    }
}
