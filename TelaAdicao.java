package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicao {

    private JFrame frame;
    private JTextField txtPrimeiroNumero;
    private JTextField txtSegundoNumero;
    private JTextField txtResultado;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaAdicao window = new TelaAdicao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaAdicao() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Calculadora de Adição");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(4, 2, 10, 10));
        frame.getContentPane().setBackground(new Color(200, 220, 240)); 
        
        JLabel lblPrimeiroNumero = new JLabel("Primeiro número:");
        frame.getContentPane().add(lblPrimeiroNumero);

        txtPrimeiroNumero = new JTextField();
        frame.getContentPane().add(txtPrimeiroNumero);
        txtPrimeiroNumero.setColumns(10);

        JLabel lblSegundoNumero = new JLabel("Segundo número:");
        frame.getContentPane().add(lblSegundoNumero);

        txtSegundoNumero = new JTextField();
        frame.getContentPane().add(txtSegundoNumero);
        txtSegundoNumero.setColumns(10);

        JLabel lblResultado = new JLabel("Resultado:");
        frame.getContentPane().add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setEditable(false);
        frame.getContentPane().add(txtResultado);
        txtResultado.setColumns(10);
        txtResultado.setBackground(new Color(220, 220, 220)); 

        JButton btnSomar = new JButton("Somar");
        btnSomar.setBackground(new Color(50, 150, 50)); 
        btnSomar.setForeground(Color.WHITE); 
        btnSomar.setFocusPainted(false);
        btnSomar.setPreferredSize(new Dimension(80, 30)); 
        btnSomar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double n1 = Double.parseDouble(txtPrimeiroNumero.getText());
                    double n2 = Double.parseDouble(txtSegundoNumero.getText());
                    double soma = n1 + n2;
                    txtResultado.setText(String.valueOf(soma));
                } catch (NumberFormatException ex) {
                    txtResultado.setText("Erro");
                }
            }
        });
        frame.getContentPane().add(btnSomar);
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(220, 50, 50)); 
        btnLimpar.setForeground(Color.WHITE); 
        btnLimpar.setFocusPainted(false);
        btnLimpar.setPreferredSize(new Dimension(80, 30));
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtPrimeiroNumero.setText("");
                txtSegundoNumero.setText("");
                txtResultado.setText("");
            }
        });
        frame.getContentPane().add(btnLimpar);
    }
}
