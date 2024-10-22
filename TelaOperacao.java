package org.cesarschool.telas;

import javax.swing.*;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaOperacao extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField campoEhAcao;
    private JTextField campoEntidadeCredito;
    private JTextField campoEntidadeDebito;
    private JTextField campoAcaoOuTitulo;
    private JTextField campoValor;
    private JLabel resultadoLabel;
    private JTextArea extratoArea;

    public TelaOperacao() {
        setTitle("Realizar Operação");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel lblTitulo = new JLabel("Realizar Operação", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 48));

        JLabel labelEhAcao = new JLabel("Eh Ação? true ou false:", SwingConstants.CENTER);
        JLabel labelEntidadeCredito = new JLabel("ID Entidade Crédito:", SwingConstants.CENTER);
        JLabel labelEntidadeDebito = new JLabel("ID Entidade Débito:", SwingConstants.CENTER);
        JLabel labelAcaoOuTitulo = new JLabel("ID Ação ou Título:", SwingConstants.CENTER);
        JLabel labelValor = new JLabel("Valor:", SwingConstants.CENTER);

        configurarLabel(labelEhAcao);
        configurarLabel(labelEntidadeCredito);
        configurarLabel(labelEntidadeDebito);
        configurarLabel(labelAcaoOuTitulo);
        configurarLabel(labelValor);

        campoEhAcao = new JTextField();
        campoEntidadeCredito = new JTextField();
        campoEntidadeDebito = new JTextField();
        campoAcaoOuTitulo = new JTextField();
        campoValor = new JTextField();

        configurarCampoTexto(campoEhAcao);
        configurarCampoTexto(campoEntidadeCredito);
        configurarCampoTexto(campoEntidadeDebito);
        configurarCampoTexto(campoAcaoOuTitulo);
        configurarCampoTexto(campoValor);

        JButton botaoOperacao = new JButton("Realizar Operação");
        JButton botaoExtrato = new JButton("Gerar Extrato");
        JButton btnVoltar = new JButton("Voltar");

        configurarBotao(botaoOperacao);
        configurarBotao(botaoExtrato);
        configurarBotao(btnVoltar);

        resultadoLabel = new JLabel("", SwingConstants.CENTER);
        resultadoLabel.setForeground(Color.WHITE);
        resultadoLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        extratoArea = new JTextArea();
        extratoArea.setEditable(false);
        extratoArea.setFont(new Font("Arial", Font.PLAIN, 18));
        extratoArea.setLineWrap(true); 
        extratoArea.setWrapStyleWord(true);
        extratoArea.setPreferredSize(new Dimension(600, 400));
        JScrollPane scrollPane = new JScrollPane(extratoArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        botaoOperacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacao();
            }
        });

        botaoExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarExtrato();
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
        panel.add(labelEhAcao);
        panel.add(campoEhAcao);
        panel.add(labelEntidadeCredito);
        panel.add(campoEntidadeCredito);
        panel.add(labelEntidadeDebito);
        panel.add(campoEntidadeDebito);
        panel.add(labelAcaoOuTitulo);
        panel.add(campoAcaoOuTitulo);
        panel.add(labelValor);
        panel.add(campoValor);
        panel.add(Box.createVerticalStrut(30));
        panel.add(botaoOperacao);
        panel.add(Box.createVerticalStrut(20));
        panel.add(botaoExtrato);
        panel.add(Box.createVerticalStrut(20));
        panel.add(resultadoLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(scrollPane);
        panel.add(btnVoltar);
        panel.add(Box.createVerticalStrut(20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));

        add(panel);
        setVisible(true);
    }

    private void configurarLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void configurarCampoTexto(JTextField campo) {
        campo.setMaximumSize(new Dimension(500, 40));
        campo.setFont(new Font("Arial", Font.PLAIN, 24));
    }

    private void configurarBotao(JButton botao) {
        botao.setBackground(new Color(30, 144, 255));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.PLAIN, 24));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(500, 60));
    }

    private void realizarOperacao() {
        try {
            boolean ehAcao = Boolean.parseBoolean(campoEhAcao.getText());
            int entidadeCredito = Integer.parseInt(campoEntidadeCredito.getText());
            int entidadeDebito = Integer.parseInt(campoEntidadeDebito.getText());
            int idAcaoOuTitulo = Integer.parseInt(campoAcaoOuTitulo.getText());
            double valor = Double.parseDouble(campoValor.getText());

            MediatorOperacao mediador = MediatorOperacao.getInstancia();
            String resultado = mediador.realizarOperacao(ehAcao, entidadeCredito, entidadeDebito, idAcaoOuTitulo, valor);
            resultadoLabel.setText(resultado);
        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Erro: Entrada inválida.");
        }
    }

    private void gerarExtrato() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Transacao.txt"))) {
            StringBuilder conteudo = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
            extratoArea.setText(conteudo.toString());
        } catch (IOException e) {
            extratoArea.setText("Erro ao ler o arquivo de transação.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaOperacao();
            }
        });
    }
}
