/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArvoreAVL;

import java.math.BigInteger;

/**
 *
 * @author Rian Alves
 */
public class No {
        private No esquerda;
	private No direita;
	private No pai;
	private BigInteger chave;
	private int balanceamento;

	public No(BigInteger k) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setChave(k);
	}

	/*public String toString() {
		return BigInteger.toString(getChave());
	}*/

	public BigInteger getChave() {
		return chave;
	}

	public void setChave(BigInteger chave) {
		this.chave = chave;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public No getPai() {
		return pai;
	}

	public No setPai(No pai) {
		this.pai = pai;
		return pai;
	}

	public No getDireita() {
		return direita;
	}

	public No setDireita(No direita) {
		this.direita = direita;
		return direita;
	}

	public No getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
}
    

