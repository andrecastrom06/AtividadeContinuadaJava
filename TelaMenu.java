package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class TelaMenu extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    public TelaMenu() {
        setTitle("Escolha de Tela");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton btnAcao = new JButton("Tela de Ação");
        JButton btnEntidadeOperadora = new JButton("Tela de Entidade Operadora");
        JButton btnTituloDivida = new JButton("Tela de Titulo Divida");
        JButton btnTransacao = new JButton("Tela de Transacao");

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
        
        btnTransacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaTransacao acaoScreen = new TelaTransacao();
                acaoScreen.setVisible(true);
                dispose(); 
            }
        });

        panel.add(btnAcao);
        panel.add(btnEntidadeOperadora);
        panel.add(btnTituloDivida);
        panel.add(btnTransacao);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaMenu mainScreen = new TelaMenu();
                mainScreen.setVisible(true);
            }
        });
    }
}
