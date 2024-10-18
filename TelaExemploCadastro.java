package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaExemploCadastro {

    private JFrame frame;
    private static EntidadeMediator mediator = new EntidadeMediator();
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtRenda;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaExemploCadastro window = new TelaExemploCadastro();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaExemploCadastro() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Tela de Cadastro");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblCodigo = new JLabel("Código:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.getContentPane().add(lblCodigo, gbc);

        txtCodigo = new JTextField(10);
        gbc.gridx = 1;
        frame.getContentPane().add(txtCodigo, gbc);

        JButton btnNovo = new JButton("Novo");
        btnNovo.setBackground(new Color(50, 150, 50));
        btnNovo.setForeground(Color.WHITE);
        gbc.gridx = 2;
        frame.getContentPane().add(btnNovo, gbc);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(50, 100, 200));
        btnBuscar.setForeground(Color.WHITE);
        gbc.gridx = 3;
        frame.getContentPane().add(btnBuscar, gbc);

        JLabel lblNome = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(lblNome, gbc);

        txtNome = new JTextField(15);
        txtNome.setEnabled(false);
        gbc.gridx = 1;
        frame.getContentPane().add(txtNome, gbc);

        JLabel lblRenda = new JLabel("Renda:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.getContentPane().add(lblRenda, gbc);

        txtRenda = new JTextField(10);
        txtRenda.setEnabled(false);
        gbc.gridx = 1;
        frame.getContentPane().add(txtRenda, gbc);

        JButton btnIncluirAlterar = new JButton("Incluir");
        btnIncluirAlterar.setEnabled(false);
        btnIncluirAlterar.setBackground(new Color(200, 150, 50));
        btnIncluirAlterar.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.getContentPane().add(btnIncluirAlterar, gbc);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.setBackground(new Color(200, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        gbc.gridx = 1;
        frame.getContentPane().add(btnCancelar, gbc);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(100, 100, 100));
        btnLimpar.setForeground(Color.WHITE);
        gbc.gridx = 2;
        frame.getContentPane().add(btnLimpar, gbc);

        btnNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Entidade ent = TelaExemploCadastro.mediator.buscar(txtCodigo.getText());
                if (ent != null) {
                    JOptionPane.showMessageDialog(frame, "Entidade já existente!");
                } else {
                    btnIncluirAlterar.setEnabled(true);
                    btnCancelar.setEnabled(true);
                    txtNome.setEnabled(true);
                    txtRenda.setEnabled(true);
                    btnNovo.setEnabled(false);
                    btnBuscar.setEnabled(false);
                    txtCodigo.setEnabled(false);
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Entidade ent = TelaExemploCadastro.mediator.buscar(txtCodigo.getText());
                if (ent == null) {
                    JOptionPane.showMessageDialog(frame, "Entidade não existente!");
                } else {
                    txtNome.setText(ent.getNome());
                    txtRenda.setText(String.valueOf(ent.getRenda()));
                    btnIncluirAlterar.setText("Alterar");
                    btnIncluirAlterar.setEnabled(true);
                    btnCancelar.setEnabled(true);
                    txtNome.setEnabled(true);
                    txtRenda.setEnabled(true);
                    btnNovo.setEnabled(false);
                    btnBuscar.setEnabled(false);
                    txtCodigo.setEnabled(false);
                }
            }
        });

        btnIncluirAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Entidade ent = new Entidade(txtCodigo.getText(), txtNome.getText(),
                        Double.parseDouble(txtRenda.getText()));
                String msg = null;
                if (btnIncluirAlterar.getText().equals("Incluir")) {
                    msg = mediator.incluir(ent);
                } else {
                    msg = mediator.alterar(ent);
                }
                if (msg != null) {
                    JOptionPane.showMessageDialog(frame, msg);
                } else {
                    btnIncluirAlterar.setEnabled(false);
                    btnCancelar.setEnabled(false);
                    txtNome.setEnabled(false);
                    txtRenda.setEnabled(false);
                    btnNovo.setEnabled(true);
                    btnBuscar.setEnabled(true);
                    txtCodigo.setEnabled(true);
                    txtCodigo.setText("");
                    txtRenda.setText("");
                    txtNome.setText("");
                    btnIncluirAlterar.setText("Incluir");
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnIncluirAlterar.setEnabled(false);
                btnCancelar.setEnabled(false);
                txtNome.setEnabled(false);
                txtRenda.setEnabled(false);
                btnNovo.setEnabled(true);
                btnBuscar.setEnabled(true);
                txtCodigo.setEnabled(true);
                txtCodigo.setText("");
                txtRenda.setText("");
                txtNome.setText("");
                btnIncluirAlterar.setText("Incluir");
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtCodigo.isEnabled()) {
                    txtCodigo.setText("");
                }
                txtRenda.setText("");
                txtNome.setText("");
            }
        });
    }
}
