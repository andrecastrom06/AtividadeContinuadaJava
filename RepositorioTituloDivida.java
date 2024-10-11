package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
 * 
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir 
 * identificador repetido. Neste caso, o método deve retornar false. Inclusão com 
 * sucesso, retorno true.
 * 
 * A alteração deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando não encontrado, enseja retorno false. 
 * Alteração com sucesso, retorno true.  
 *   
 * A exclusão deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando não encontrado, enseja retorno false. 
 * Exclusão com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador não seja encontrado no arquivo, retornar null.   
 */

public class RepositorioTituloDivida {

    public boolean incluir(TituloDivida tituloDivida) {
        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; 
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == tituloDivida.getIdentificador()) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter("TituloDivida.txt", true))) {
            pw.println(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + 
                       tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros());
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(TituloDivida tituloDivida) {
        StringBuilder conteudo = new StringBuilder();
        boolean identificadorEncontrado = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; 
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == tituloDivida.getIdentificador()) {
                    linha = tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + 
                            tituloDivida.getDataDeValidade() + ";" + tituloDivida.getTaxaJuros();
                    identificadorEncontrado = true;
                }
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (identificadorEncontrado) {
            try (PrintWriter pw = new PrintWriter(new FileWriter("TituloDivida.txt"))) {
                pw.print(conteudo.toString());
                return true; 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean excluir(int identificador) {
        StringBuilder conteudo = new StringBuilder();
        boolean identificadorEncontrado = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; 
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == identificador) {
                    identificadorEncontrado = true;
                    continue;
                }
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (identificadorEncontrado) {
            try (PrintWriter pw = new PrintWriter(new FileWriter("TituloDivida.txt"))) {
                pw.print(conteudo.toString());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public TituloDivida buscar(int identificador) {
        try (BufferedReader reader = new BufferedReader(new FileReader("TituloDivida.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == identificador) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    LocalDate dataDeValidade = LocalDate.parse(dados[2]);
                    double taxaJuros = Double.parseDouble(dados[3]);
                    return new TituloDivida(id, nome, dataDeValidade, taxaJuros); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
