package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaEntidadeOperadora extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador, txtNome, txtAutorizadoAcao, txtSaldoAcao, txtSaldoTituloDivida;
    private MediatorEntidadeOperadora mediator = MediatorEntidadeOperadora.getInstanciaSingleton();

    public TelaEntidadeOperadora() {
        setTitle("Gestão de Entidade Operadora");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel lblIdentificador = new JLabel("Identificador:");
        txtIdentificador = new JTextField();
        
        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação (true/false):");
        txtAutorizadoAcao = new JTextField();

        JLabel lblSaldoAcao = new JLabel("Saldo Ação:");
        txtSaldoAcao = new JTextField();

        JLabel lblSaldoTituloDivida = new JLabel("Saldo Título Dívida:");
        txtSaldoTituloDivida = new JTextField();

        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Color labelColor = Color.WHITE;
        
        lblIdentificador.setForeground(labelColor);
        lblIdentificador.setFont(labelFont);
        lblNome.setForeground(labelColor);
        lblNome.setFont(labelFont);
        lblAutorizadoAcao.setForeground(labelColor);
        lblAutorizadoAcao.setFont(labelFont);
        lblSaldoAcao.setForeground(labelColor);
        lblSaldoAcao.setFont(labelFont);
        lblSaldoTituloDivida.setForeground(labelColor);
        lblSaldoTituloDivida.setFont(labelFont);

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
                EntidadeOperadora entidade = criarEntidade();
                String resultado = mediator.incluir(entidade);
                JOptionPane.showMessageDialog(null, resultado == null ? "Entidade incluída com sucesso!" : resultado);
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntidadeOperadora entidade = criarEntidade();
                String resultado = mediator.alterar(entidade);
                JOptionPane.showMessageDialog(null, resultado == null ? "Entidade alterada com sucesso!" : resultado);
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int identificador = Integer.parseInt(txtIdentificador.getText());
                String resultado = mediator.excluir(identificador);
                JOptionPane.showMessageDialog(null, resultado == null ? "Entidade excluída com sucesso!" : resultado);
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int identificador = Integer.parseInt(txtIdentificador.getText());
                EntidadeOperadora entidade = mediator.buscar(identificador);
                if (entidade != null) {
                    preencherCampos(entidade);
                    JOptionPane.showMessageDialog(null, "Entidade encontrada!");
                } else {
                    JOptionPane.showMessageDialog(null, "Entidade não encontrada!");
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
        panel.add(lblIdentificador);
        panel.add(txtIdentificador);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblAutorizadoAcao);
        panel.add(txtAutorizadoAcao);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblSaldoAcao);
        panel.add(txtSaldoAcao);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblSaldoTituloDivida);
        panel.add(txtSaldoTituloDivida);
        panel.add(Box.createVerticalStrut(50));
        panel.add(btnIncluir);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnAlterar);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnExcluir);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnBuscar);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnVoltar);
        
        panel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        add(panel);
    }

    private EntidadeOperadora criarEntidade() {
        int identificador = Integer.parseInt(txtIdentificador.getText());
        String nome = txtNome.getText();
        boolean autorizadoAcao = Boolean.parseBoolean(txtAutorizadoAcao.getText());
        double saldoAcao = Double.parseDouble(txtSaldoAcao.getText());
        double saldoTituloDivida = Double.parseDouble(txtSaldoTituloDivida.getText());

        return new EntidadeOperadora(identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida);
    }

    private void preencherCampos(EntidadeOperadora entidade) {
        txtIdentificador.setText(String.valueOf(entidade.getIdentificador()));
        txtNome.setText(entidade.getNome());
        txtAutorizadoAcao.setText(String.valueOf(entidade.getAutorizadoAcao()));
        txtSaldoAcao.setText(String.valueOf(entidade.getSaldoAcao()));
        txtSaldoTituloDivida.setText(String.valueOf(entidade.getSaldoTituloDivida()));
    }

    private void configurarBotao(JButton botao, Color bgColor, Color fgColor) {
        botao.setBackground(bgColor);
        botao.setForeground(fgColor);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.PLAIN, 24));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(500, 60));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaEntidadeOperadora tela = new TelaEntidadeOperadora();
            tela.setVisible(true);
        });
    }
}
