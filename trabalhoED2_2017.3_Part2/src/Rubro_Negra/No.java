/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rubro_Negra;

import java.math.BigInteger;

/**
 *
 * @author Rian Alves
 */
public class No {
    private BigInteger valor;
    private boolean cor; //false-preto, true- vermelho
    private No fesq;
    private No fdir;
    private No pai;

    public No() {
        this.cor = false;
        this.fdir = null;
        this.fesq =null;
        this.pai = null;
        
    }
    
    public No(BigInteger valor, boolean cor, No fesq, No fdir) {
        this.valor = valor;
        this.cor = cor;
        this.fesq = fesq;
        this.fdir = fdir;
        this.pai = pai;
    }

    /**
     * @return the valor
     */
    public BigInteger getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    /**
     * @return the cor
     */
    public boolean isCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(boolean cor) {
        this.cor = cor;
    }

    /**
     * @return the fesq
     */
    public No getFesq() {
        return fesq;
    }

    /**
     * @param fesq the fesq to set
     */
    public void setFesq(No fesq) {
        this.fesq = fesq;
    }

    /**
     * @return the fdir
     */
    public No getFdir() {
        return fdir;
    }

    /**
     * @param fdir the fdir to set
     */
    public void setFdir(No fdir) {
        this.fdir = fdir;
    }

    /**
     * @return the pai
     */
    public No getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(No pai) {
        this.pai = pai;
    }    
}
