package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import java.time.LocalDateTime;

public class TelaTransacao extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    private JTextField txtEntidadeCredito, txtEntidadeDebito, txtValorOperacao, txtDataHora;
    private RepositorioTransacao repositorio = new RepositorioTransacao();

    public TelaTransacao() {
        setTitle("Gestão de Transações");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Entidade Credora ID:"));
        txtEntidadeCredito = new JTextField();
        add(txtEntidadeCredito);

        add(new JLabel("Entidade Devedora ID:"));
        txtEntidadeDebito = new JTextField();
        add(txtEntidadeDebito);

        add(new JLabel("Valor da Operação:"));
        txtValorOperacao = new JTextField();
        add(txtValorOperacao);

        add(new JLabel("Data e Hora da Operação (AAAA-MM-DDTHH:MM):"));
        txtDataHora = new JTextField();
        add(txtDataHora);

        JButton btnIncluir = new JButton("Incluir");
        JButton btnBuscar = new JButton("Buscar por Credor");
        JButton btnVoltar = new JButton("Voltar");

        add(btnIncluir);
        add(btnBuscar);
        add(btnVoltar);

        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirTransacao();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTransacaoPorCredor();
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

    private void incluirTransacao() {
        int idCredito = Integer.parseInt(txtEntidadeCredito.getText());
        int idDebito = Integer.parseInt(txtEntidadeDebito.getText());
        double valorOperacao = Double.parseDouble(txtValorOperacao.getText());
        LocalDateTime dataHora = LocalDateTime.parse(txtDataHora.getText());
        
        EntidadeOperadora entidadeCredito = new EntidadeOperadora(idCredito, "Credor", true, 1000.0, 200.0);
        EntidadeOperadora entidadeDebito = new EntidadeOperadora(idDebito, "Devedor", true, 800.0, 100.0);
        
        Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, null, null, valorOperacao, dataHora);
        if (repositorio.incluir(transacao)) {
            JOptionPane.showMessageDialog(this, "Transação incluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao incluir transação.");
        }
    }

    private void buscarTransacaoPorCredor() {
        int idCredito = Integer.parseInt(txtEntidadeCredito.getText());
        Transacao[] transacoes = repositorio.buscarPorEntidadeCredora(idCredito);
        
        if (transacoes.length > 0) {
            StringBuilder resultado = new StringBuilder();
            for (Transacao transacao : transacoes) {
                resultado.append("Valor: ").append(transacao.getValorOperacao())
                          .append(", Data/Hora: ").append(transacao.getDataHoraOperacao()).append("\n");
            }
            JOptionPane.showMessageDialog(this, resultado.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma transação encontrada.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaTransacao().setVisible(true);
        });
    }
}
