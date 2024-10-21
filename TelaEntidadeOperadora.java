package org.cesarschool.telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class TelaEntidadeOperadora extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador, txtNome, txtAutorizadoAcao, txtSaldoAcao;
    
    public TelaEntidadeOperadora() {
        setTitle("Tela de Entidade Operadora");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel lblIdentificador = new JLabel("Identificador:");
        txtIdentificador = new JTextField();

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação (true/false):");
        txtAutorizadoAcao = new JTextField();

        JLabel lblSaldoAcao = new JLabel("Saldo Ação:");
        txtSaldoAcao = new JTextField();

        JLabel lblSaldoTituloDivida = new JLabel("Saldo Título Dívida:");
        new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnVoltar = new JButton("Voltar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
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

        panel.add(lblIdentificador);
        panel.add(txtIdentificador);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblAutorizadoAcao);
        panel.add(txtAutorizadoAcao);
        panel.add(lblSaldoAcao);
        panel.add(txtSaldoAcao);
        panel.add(lblSaldoTituloDivida);
        panel.add(btnSalvar);
        panel.add(btnVoltar);

        add(panel);
    }
}
