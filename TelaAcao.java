package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class TelaAcao extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField txtIdentificador, txtNome, txtDataValidade, txtValorUnitario;
    
    public TelaAcao() {
        setTitle("Tela de Ação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblIdentificador = new JLabel("Identificador:");
        txtIdentificador = new JTextField();

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        JLabel lblDataValidade = new JLabel("Data Validade (yyyy-mm-dd):");
        txtDataValidade = new JTextField();

        JLabel lblValorUnitario = new JLabel("Valor Unitário:");
        txtValorUnitario = new JTextField();

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
        panel.add(lblDataValidade);
        panel.add(txtDataValidade);
        panel.add(lblValorUnitario);
        panel.add(txtValorUnitario);
        panel.add(btnSalvar);
        panel.add(btnVoltar);

        add(panel);
    }
}
