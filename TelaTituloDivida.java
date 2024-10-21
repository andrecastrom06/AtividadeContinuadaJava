package org.cesarschool.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;
import java.time.LocalDate;

public class TelaTituloDivida extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtNome, txtDataValidade, txtTaxaJuros;
    private RepositorioTituloDivida repositorio = new RepositorioTituloDivida();

    public TelaTituloDivida() {
        setTitle("Gestão de Títulos de Dívida");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Data Validade (AAAA-MM-DD):"));
        txtDataValidade = new JTextField();
        add(txtDataValidade);

        add(new JLabel("Taxa de Juros:"));
        txtTaxaJuros = new JTextField();
        add(txtTaxaJuros);

        JButton btnIncluir = new JButton("Incluir");
        JButton btnAlterar = new JButton("Alterar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnVoltar = new JButton("Voltar");
        
        add(btnIncluir);
        add(btnAlterar);
        add(btnExcluir);
        add(btnBuscar);
        add(btnVoltar);

        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirTitulo();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarTitulo();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTitulo();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTitulo();
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

    private void incluirTitulo() {
        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText());
        double taxaJuros = Double.parseDouble(txtTaxaJuros.getText());
        
        TituloDivida titulo = new TituloDivida(id, nome, dataValidade, taxaJuros);
        if (repositorio.incluir(titulo)) {
            JOptionPane.showMessageDialog(this, "Título incluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao incluir título ou identificador já existe.");
        }
    }

    private void alterarTitulo() {
        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText());
        double taxaJuros = Double.parseDouble(txtTaxaJuros.getText());
        
        TituloDivida titulo = new TituloDivida(id, nome, dataValidade, taxaJuros);
        if (repositorio.alterar(titulo)) {
            JOptionPane.showMessageDialog(this, "Título alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao alterar título ou identificador não encontrado.");
        }
    }

    private void excluirTitulo() {
        int id = Integer.parseInt(txtId.getText());
        if (repositorio.excluir(id)) {
            JOptionPane.showMessageDialog(this, "Título excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir título ou identificador não encontrado.");
        }
    }

    private void buscarTitulo() {
        int id = Integer.parseInt(txtId.getText());
        TituloDivida titulo = repositorio.buscar(id);
        if (titulo != null) {
            txtNome.setText(titulo.getNome());
            txtDataValidade.setText(titulo.getDataDeValidade().toString());
            txtTaxaJuros.setText(String.valueOf(titulo.getTaxaJuros()));
            JOptionPane.showMessageDialog(this, "Título encontrado!");
        } else {
            JOptionPane.showMessageDialog(this, "Título não encontrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaTituloDivida().setVisible(true);
        });
    }
}
