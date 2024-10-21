package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class TelaMenu extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    public TelaMenu() {
        setTitle("Atividade Continuada Java");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel lblTitulo = new JLabel("Atividade Continuada Java", SwingConstants.CENTER);
        JLabel lblAutores = new JLabel("André Castro e Lucas Sukar", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblAutores.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));
        lblAutores.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton btnAcao = new JButton("Tela de Ação");
        JButton btnEntidadeOperadora = new JButton("Tela de Entidade Operadora");
        JButton btnTituloDivida = new JButton("Tela de Titulo Divida");
        JButton btnOperacao = new JButton("Tela de Operação");

        Color azul = new Color(30, 144, 255);
        Color branco = Color.WHITE;

        configurarBotao(btnAcao, azul, branco);
        configurarBotao(btnEntidadeOperadora, azul, branco);
        configurarBotao(btnTituloDivida, azul, branco);
        configurarBotao(btnOperacao, azul, branco);

        btnAcao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAcao acaoScreen = new TelaAcao();
                acaoScreen.setVisible(true);
                dispose();
            }
        });

        btnEntidadeOperadora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaEntidadeOperadora entidadeScreen = new TelaEntidadeOperadora();
                entidadeScreen.setVisible(true);
                dispose();
            }
        });

        btnTituloDivida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaTituloDivida acaoScreen = new TelaTituloDivida();
                acaoScreen.setVisible(true);
                dispose();
            }
        });

        btnOperacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaTransacao acaoScreen = new TelaTransacao();
                acaoScreen.setVisible(true);
                dispose();
            }
        });

        panel.add(Box.createVerticalStrut(100));
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblAutores);
        panel.add(Box.createVerticalStrut(50));
        panel.add(btnAcao);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnEntidadeOperadora);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnTituloDivida);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnOperacao);

        panel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        add(panel);
        setVisible(true);
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaMenu();
            }
        });
    }
}
