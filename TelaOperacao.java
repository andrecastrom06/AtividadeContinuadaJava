package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

public class TelaOperacao extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    private JTextField txtEntidadeCredito, txtEntidadeDebito, txtAcaoOuTitulo, txtValor;
    private JCheckBox checkEhAcao;
    private MediatorOperacao mediator = MediatorOperacao.getInstancia();

    public TelaOperacao() {
        setTitle("Gestão de Operações");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel lblEntidadeCredito = new JLabel("Entidade Credora ID:", SwingConstants.CENTER);
        txtEntidadeCredito = new JTextField();
        JLabel lblEntidadeDebito = new JLabel("Entidade Devedora ID:", SwingConstants.CENTER);
        txtEntidadeDebito = new JTextField();
        JLabel lblAcaoOuTitulo = new JLabel("ID Ação/Título:", SwingConstants.CENTER);
        txtAcaoOuTitulo = new JTextField();
        JLabel lblValor = new JLabel("Valor da Operação:", SwingConstants.CENTER);
        txtValor = new JTextField();
        JLabel lblEhAcao = new JLabel("É Ação?", SwingConstants.CENTER);
        checkEhAcao = new JCheckBox();

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        lblEntidadeCredito.setFont(labelFont);
        lblEntidadeDebito.setFont(labelFont);
        lblAcaoOuTitulo.setFont(labelFont);
        lblValor.setFont(labelFont);
        lblEhAcao.setFont(labelFont);
        lblEntidadeCredito.setForeground(Color.WHITE);
        lblEntidadeDebito.setForeground(Color.WHITE);
        lblAcaoOuTitulo.setForeground(Color.WHITE);
        lblValor.setForeground(Color.WHITE);
        lblEhAcao.setForeground(Color.WHITE);
        
        JButton btnRealizarOperacao = new JButton("Realizar Operação");
        JButton btnGerarExtrato = new JButton("Gerar Extrato");
        JButton btnVoltar = new JButton("Voltar");

        Color azul = new Color(30, 144, 255);
        Color branco = Color.WHITE;
        configurarBotao(btnRealizarOperacao, azul, branco);
        configurarBotao(btnGerarExtrato, azul, branco);
        configurarBotao(btnVoltar, azul, branco);

        panel.add(Box.createVerticalStrut(50));
        panel.add(lblEntidadeCredito);
        panel.add(txtEntidadeCredito);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblEntidadeDebito);
        panel.add(txtEntidadeDebito);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblAcaoOuTitulo);
        panel.add(txtAcaoOuTitulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblValor);
        panel.add(txtValor);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblEhAcao);
        panel.add(checkEhAcao);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnRealizarOperacao);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnGerarExtrato);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnVoltar);
        panel.add(Box.createVerticalStrut(50));

        panel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));
        add(panel);
        setVisible(true);

        btnRealizarOperacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacao();
            }
        });

        btnGerarExtrato.addActionListener(new ActionListener() {
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
    }

    private void configurarBotao(JButton botao, Color bgColor, Color fgColor) {
        botao.setBackground(bgColor);
        botao.setForeground(fgColor);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.PLAIN, 24));
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(500, 60));
    }

    private void realizarOperacao() {
        try {
            int idCredito = Integer.parseInt(txtEntidadeCredito.getText());
            int idDebito = Integer.parseInt(txtEntidadeDebito.getText());
            int idAcaoOuTitulo = Integer.parseInt(txtAcaoOuTitulo.getText());
            double valorOperacao = Double.parseDouble(txtValor.getText());
            boolean ehAcao = checkEhAcao.isSelected();

            String resultado = mediator.realizarOperacao(ehAcao, idCredito, idDebito, idAcaoOuTitulo, valorOperacao);
            JOptionPane.showMessageDialog(this, resultado);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: IDs e valor da operação devem ser numéricos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao processar a operação: " + ex.getMessage());
        }
    }

    private void gerarExtrato() {
        try {
            int idEntidade = Integer.parseInt(txtEntidadeCredito.getText());
            Transacao[] extrato = mediator.gerarExtrato(idEntidade);

            if (extrato != null && extrato.length > 0) {
                StringBuilder resultado = new StringBuilder();
                for (Transacao transacao : extrato) {
                    resultado.append("Entidade Débito: ")
                             .append(transacao.getEntidadeDebito().getIdentificador())
                             .append(", Valor: ").append(transacao.getValorOperacao())
                             .append(", Data/Hora: ").append(transacao.getDataHoraOperacao())
                             .append("\n");
                }
                JOptionPane.showMessageDialog(this, resultado.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma transação encontrada.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: ID da entidade deve ser numérico.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar extrato: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaOperacao().setVisible(true);
        });
    }
}
