package br.com.cesarschool.poo.titulos.entidades;

// Esta classe deve herdar de Ativo. E deve ter os seguintes atributos: valorUnitario, do tipo double. 
// Deve ter um construtor público que inicializa os atributos, e métodos set/get públicos para os atributos. 
// Deve ter um método público double calcularPrecoTransacao(double montante): o preço da transação é o produto do montante pelo valorUnitario.

import java.time.LocalDate;

public class Acao extends Ativo {
	private double valorUnitario;

	public Acao(int identificador, String nome, LocalDate datadDeValidade, double valorUnitario) {
		super(identificador, nome, datadDeValidade);
		this.valorUnitario = valorUnitario;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public double calcularPrecoTransacao(double montante) {
		return montante * valorUnitario;
	}
}
