package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
 * 
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */

public class RepositorioAcao {
    public boolean incluir(Acao acao) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Acao.txt", true));
            if (buscar(acao.getIdentificador()) != null) {
                pw.close();
                return false;
            }
            pw.println(acao.getIdentificador() + ";" + acao.getNome() + ";" +
                       acao.getDataDeValidade() + ";" + acao.getValorUnitario());
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Acao acao) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"));
            StringBuilder conteudo = new StringBuilder();
            String linha;
            boolean identificadorEncontrado = false;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == acao.getIdentificador()) {
                    linha = acao.getIdentificador() + ";" + acao.getNome() + ";" +
                            acao.getDataDeValidade() + ";" + acao.getValorUnitario();
                    identificadorEncontrado = true;
                }
                conteudo.append(linha).append("\n");
            }
            reader.close();
            if (identificadorEncontrado) {
                PrintWriter pw = new PrintWriter(new FileWriter("Acao.txt"));
                pw.print(conteudo.toString());
                pw.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean excluir(int identificador) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"));
            StringBuilder conteudo = new StringBuilder();
            String linha;
            boolean identificadorEncontrado = false;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == identificador) {
                    identificadorEncontrado = true;
                    continue; 
                }
                conteudo.append(linha).append("\n");
            }
            reader.close();
            if (identificadorEncontrado) {
                PrintWriter pw = new PrintWriter(new FileWriter("Acao.txt"));
                pw.print(conteudo.toString());
                pw.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Acao buscar(int identificador) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == identificador) {
                    String nome = dados[1];
                    LocalDate dataDeValidade = LocalDate.parse(dados[2]);
                    double valorUnitario = Double.parseDouble(dados[3]);
                    reader.close();
                    return new Acao(identificador, nome, dataDeValidade, valorUnitario);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
