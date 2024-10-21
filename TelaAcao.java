package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaAcao extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador, txtNome, txtDataValidade, txtValorUnitario;
    private MediatorAcao mediatorAcao;

    public TelaAcao() {
        mediatorAcao = MediatorAcao.getInstanciaSingleton();
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
                Acao acao = criarAcao();
                String resultado = mediatorAcao.incluir(acao);
                if (resultado == null) {
                    JOptionPane.showMessageDialog(null, "Dados incluídos com sucesso!");
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(null, resultado);
                }
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Acao acao = criarAcao();
                String resultado = mediatorAcao.alterar(acao);
                if (resultado == null) {
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(null, resultado);
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int identificador = Integer.parseInt(txtIdentificador.getText());
                String resultado = mediatorAcao.excluir(identificador);
                if (resultado == null) {
                    JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(null, resultado);
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int identificador = Integer.parseInt(txtIdentificador.getText());
                Acao acao = mediatorAcao.buscar(identificador);
                if (acao != null) {
                    txtNome.setText(acao.getNome());
                    txtDataValidade.setText(acao.getDataDeValidade().toString());
                    txtValorUnitario.setText(String.valueOf(acao.getValorUnitario()));
                } else {
                    JOptionPane.showMessageDialog(null, "Ação não encontrada.");
                }
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

    private Acao criarAcao() {
        int identificador = Integer.parseInt(txtIdentificador.getText());
        String nome = txtNome.getText();
        LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText());
        double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
        return new Acao(identificador, nome, dataValidade, valorUnitario);
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
